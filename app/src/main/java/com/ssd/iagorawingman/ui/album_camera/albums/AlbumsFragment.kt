package com.ssd.iagorawingman.ui.album_camera.albums

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.databinding.FragmentAlbumBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


class AlbumsFragment : Fragment(), AlbumsAdapter.ItemCallBackAdapter {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumsAdapter: AlbumsAdapter
    private var allPictures: ArrayList<Image> = ArrayList()
    private var selectedImage: ArrayList<Image> = ArrayList()
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentAlbumBinding.inflate(inflater, container, false)
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



    private fun getAllImages(): ArrayList<Image> {
        val columnIndexID: Int
        val images = ArrayList<Image>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME)
        var cursor = requireActivity().contentResolver.query(allImageUri, projection, null, null, MediaStore.Images.ImageColumns.DATE_ADDED + " DESC")
        var imageId: Long

        try{
            cursor!!.moveToFirst()
//            columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            do{
                val image = Image()
//                val uriImage = Uri.withAppendedPath(allImageUri, "" + cursor.getLong(columnIndexID))

                image.imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.imageName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while (cursor.moveToNext())
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }

        return images
    }

    override fun onSelectedImage(result: ArrayList<Image>) {
        selectedImage = result
        sharedViewModel.SharedImageSelected(result)
       println("WOKWOWJOIWJIOW ${result}")
    }

}