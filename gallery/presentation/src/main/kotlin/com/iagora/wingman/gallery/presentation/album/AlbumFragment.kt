package com.iagora.wingman.gallery.presentation.album

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.presentation.util.Permission
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import com.iagora.wingman.gallery.domain.models.Image
import com.iagora.wingman.gallery.presentation.R
import com.iagora.wingman.gallery.presentation.databinding.FragmentAlbumBinding


class AlbumFragment :
    BottomSheetDialogFragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupToolbar()
    }

    private fun setupAlbum() {
        if (isAlbumNotGrantedPermission()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        } else {
            adapter.submitList(getAllImages())
        }


        binding?.btnPermission?.setOnClickListener {
            Permission.forceFullOpenPermission(requireActivity())
        }
    }


    override fun onStart() {
        super.onStart()
        setupAlbum()
    }


    override fun onResume() {
        super.onResume()
        changePermissionViewTo(isAlbumNotGrantedPermission())

    }

    private fun changePermissionViewTo(visible: Boolean) {
        binding?.apply {
            tvInformationPermission.isVisible = visible
            btnPermission.isVisible = visible
            rvListAlbum.isVisible = !visible
        }
    }

    private fun setupAdapter() {
        adapter = AlbumAdapter()

        binding?.rvListAlbum?.adapter = adapter
    }

    private fun setupToolbar() {
        binding?.incHeader?.apply {
            btnCloseGallery.apply {
                setImageResource(R.drawable.ic_arrow_left)
                setOnClickListener {
                    dialog?.dismiss()
                }
            }
            btnSelectGallery.setOnClickListener {
                requireActivity().finish()
            }
            tvToolbar.setTextColor(resources.getColor(R.color.black, null))

            adapter.getToolbarText { textToolbar ->
                if (textToolbar == null) {

                    btnSelectGallery.isVisible = false
                    tvToolbar.isVisible = false

                } else {
                    btnSelectGallery.isVisible = true
                    tvToolbar.apply {
                        text = textToolbar
                        isVisible = true
                    }
                }
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)

        dialog.setOnShowListener { IDialog ->
            val bottomSheetDialog = IDialog as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                val behaviour = BottomSheetBehavior.from(view)
                setupFullHeight(view)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun isAlbumNotGrantedPermission() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) != PackageManager.PERMISSION_GRANTED


    private fun getAllImages(): List<Image> {
        val progressBar = binding?.progressBar

        val columnIndexID: Int
        val images = mutableListOf<Image>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val cursor = requireActivity().contentResolver.query(
            allImageUri,
            projection,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_ADDED + " DESC"
        )

        try {
            progressBar?.isVisible = true
            cursor!!.moveToFirst()
            columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            do {
                images.add(
                    Image(
                        imageName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
                        imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)),
                        imageUri = Uri.withAppendedPath(
                            allImageUri,
                            "" + cursor.getLong(columnIndexID)
                        )
                    )
                )
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Throwable) {
            Snackbar.make(binding?.content!!, e.message.toString(), Snackbar.LENGTH_SHORT)
                .apply { customPrimaryColor(requireContext()) }.show()
        } finally {
            progressBar?.isVisible = false
        }

        return images
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MAX_IMAGE = 9
        private const val REQUEST_CODE_PERMISSION = 111
    }
}