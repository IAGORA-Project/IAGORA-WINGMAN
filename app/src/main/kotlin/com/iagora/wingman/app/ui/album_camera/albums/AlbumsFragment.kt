package com.iagora.wingman.app.ui.album_camera.albums

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iagora.wingman.app.databinding.FragmentAlbumBinding
import com.iagora.wingman.core.source.local.model.ImageModel
import com.iagora.wingman.core.source.local.shared_view_model.SharedViewModel


class AlbumsFragment : Fragment(), AlbumsAdapter.ItemCallBackAdapter {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumsAdapter: AlbumsAdapter
    private var allPictures: ArrayList<ImageModel> = ArrayList()
    private var selectedImageModel: ArrayList<ImageModel> = ArrayList()
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkedStorageAndPermission()
    }


    private fun checkedStorageAndPermission(){
        // Storage Permissions
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }

        binding.ProgressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            if(allPictures.isEmpty()){
                allPictures = getAllImages()
                albumsAdapter = AlbumsAdapter(allPictures, this, requireContext())
                binding.rvListAlbum.apply {
                    adapter = albumsAdapter
                    layoutManager = GridLayoutManager(context, 3)
                    setHasFixedSize(true)
                }
            }
            binding.ProgressBar.visibility = View.GONE

        },1000)
    }



    private fun getAllImages(): ArrayList<ImageModel> {
        val columnIndexID: Int
        val images = ArrayList<ImageModel>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        var cursor = requireActivity().contentResolver.query(allImageUri, projection, null, null, MediaStore.Images.ImageColumns.DATE_ADDED + " DESC")
        var imageId: Long

        try{
            cursor!!.moveToFirst()
            columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            do{
                val image = ImageModel()
                val uriImage = Uri.withAppendedPath(allImageUri, "" + cursor.getLong(columnIndexID))

                image.imageUri = uriImage
                image.imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                image.imageName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while (cursor.moveToNext())
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }

        return images
    }



    override fun onSelectedImage(result: ArrayList<ImageModel>) {
        sharedViewModel.tempImageSelected(result)
    }

}