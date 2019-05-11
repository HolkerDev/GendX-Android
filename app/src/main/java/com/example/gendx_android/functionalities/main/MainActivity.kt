package com.example.gendx_android.functionalities.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.gendx_android.R
import com.example.gendx_android.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_TAKE_PHOTO = 25
    private val REQUEST_OPEN_GALLERY = 26

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region viewModel init
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.mainViewModel = viewModel
        initObservables()
        //endregion
    }


    fun initObservables() {
        viewModel.event.observe(this, Observer { event ->
            when (event) {
                MainEvents.TakePhoto -> {
                    val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO)
                }
                MainEvents.ChooseFromGallery -> {
                    val i = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    i.type = "image/*"
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), REQUEST_OPEN_GALLERY)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_TAKE_PHOTO -> {
                    val imageBitmap = data.extras?.get("data") as Bitmap
                    main_iv_photo.setImageBitmap(imageBitmap)
                }
                REQUEST_OPEN_GALLERY -> {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
                    main_iv_photo.setImageBitmap(bitmap)
                }
            }
        }
    }

}
