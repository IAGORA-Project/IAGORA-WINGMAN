package com.iagora.wingman.market.presentation.add_product

import android.net.Uri
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.Permission
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.presentation.util.asString
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentAddProductBinding
import com.iagora.wingman.gallery.presentation.camera.CameraFragment.Companion.PRODUCT
import com.iagora.wingman.market.presentation.list_product.ListProductFragment.Companion.defaultMarket
import kotlinx.coroutines.flow.collectLatest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.create
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream

class AddProductFragment : BaseFragment<FragmentAddProductBinding>(
    R.layout.fragment_add_product,
    { FragmentAddProductBinding.bind(it) }) {

    private val viewModel: AddProductViewModel by inject()

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_add_product)
    }

    override fun setView() {

        viewModel.listTypeAndCategory.collectWhenStarted(viewLifecycleOwner) { state ->
            setToggleView(state.isLoading)
            state.data?.let {
                val arr = it.map { it.name }
                binding.dropdownChildCategoryProduct.setDropDown(arr)
            }

            val productOum = arrayListOf("/Kg", "/Sisir", "/Ikat", "/Pcs")
            binding.layoutPricing.dropProductOum.setDropDown(productOum)
            binding.layoutPricing.dropProductOum.setSelection(0)

            val tipeProduct = arrayListOf("A", "B", "C")
            binding.dropdownChildTypeProduct.setDropDown(tipeProduct)

//            setupUI(state.data?.success)
        }



        setupCreateProduct()
        observerEventFlow()
//        setupNavigation()
    }


    private fun setToggleView(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
//            containerBtn.root.isVisible = !isLoading
            nestedScrollView.isVisible = !isLoading
        }
    }

    private fun observerEventFlow() {
        viewModel.eventFlow.collectWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                is UiEvent.CreateMessage -> {
                    Snackbar.make(
                        binding.root,
                        event.uiText.asString(requireContext()),
                        Snackbar.LENGTH_SHORT
                    ).apply {
                        customPrimaryColor(this.context)
                    }.show()
                }
            }
        }
    }

    private fun setTempFile(uri: Uri): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val outputStream = ByteArrayOutputStream()
        inputStream!!.copyTo(outputStream, minOf(1024, inputStream.available()))
        return outputStream.toByteArray() ?: byteArrayOf(0)
    }

    private fun setupCreateProduct() {
        binding.cardImageProduct.setOnClickListener {
            openCamera(PRODUCT)
        }

        findNavController().currentBackStackEntryFlow.collectLatestWhenStarted(
            viewLifecycleOwner
        ) { nav ->
            nav.savedStateHandle.get<Uri>(PRODUCT)?.let { uriProduct ->
                binding.imgProduct.apply {
                    load(uriProduct.toString())
                    uriProd = uriProduct
                }
            }
        }


        binding.apply {
            btnAddProduct.setOnClickListener {
                val map: HashMap<String, RequestBody> = HashMap()
                map.put("product_name", createPartFromString(etName.text.toString()))
                map.put("product_grade", createPartFromString(dropdownChildTypeProduct.text.toString()))
                map.put("product_category", createPartFromString(dropdownChildCategoryProduct.text.toString()))
                map.put("product_price", createPartFromString(layoutPricing.tfPrice.editText?.text.toString()))
                map.put("product_uom", createPartFromString(layoutPricing.dropProductOum.text.toString()))
                map.put("marketId", createPartFromString(defaultMarket))

                val productImage =
                    uriProd?.let {
                        MultipartBody.Part.createFormData(
                            "product_image",
                            "${System.currentTimeMillis()}.jpg",
                            setTempFile(it).toRequestBody("image/JPG".toMediaTypeOrNull())
                        )
                    }

                productImage?.let {
                    viewModel.postNewProduct(map, productImage)
                }
            }
        }


    }

    fun createPartFromString(desc: String): RequestBody {
        return desc.toRequestBody(MultipartBody.FORM)
    }

    private fun openCamera(typeImage: String) {
        if (!Permission.hasPermission(requireActivity(), android.Manifest.permission.CAMERA)) {
            launcherPermission.launch(android.Manifest.permission.CAMERA)
        } else {
            findNavController().navigate(AddProductFragmentDirections.openCamera(typeImage))
        }
    }


    val launcherPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                openCamera(PRODUCT)
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    companion object {
        var uriProd: Uri? = null

        data class TempListVariant(
            val id: Int,
            val unit: String,
            var price: Long? = 0,
            var variant: Long? = 0,
        )
    }


    private fun AutoCompleteTextView.setDropDown(listString: List<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_text, listString)
        setAdapter(arrayAdapter)
    }
}