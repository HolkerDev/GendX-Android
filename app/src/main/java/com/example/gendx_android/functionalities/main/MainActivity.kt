package com.example.gendx_android.functionalities.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gendx_android.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gendx_android.data.model.ResponseMany
import com.example.gendx_android.data.model.ResponseOne
import com.example.gendx_android.data.repo.GendXApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val REQUEST_TAKE_PHOTO = 25
    private val REQUEST_OPEN_GALLERY = 26

    private val BASE_URL = "http://18.188.155.84:5000"

    lateinit var retrofit: Retrofit

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: MainViewModel

    lateinit var result: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region viewModel init
        binding = DataBindingUtil.setContentView(this, com.example.gendx_android.R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.mainViewModel = viewModel
        initObservables()
        //endregion
    }


    private fun getGender(type: GendXType) {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GendXApiService::class.java)

        when (type) {
            GendXType.Many -> {
                val callMany = service.getGenderMany(result)
                callMany.enqueue(object : Callback<ResponseMany> {
                    override fun onFailure(call: Call<ResponseMany>, t: Throwable) {
                        hideProgressBar()
                        Log.i(TAG, t.message)
                        Toast.makeText(applicationContext, "Error, try again later", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ResponseMany>, response: Response<ResponseMany>) {
                        hideProgressBar()
                        val genders = response.body()?.people
                        // TODO: Implement recycler view for this
                        Toast.makeText(applicationContext, genders?.get(0)?.gender, Toast.LENGTH_LONG).show()
                    }
                })
            }
            GendXType.One -> {
                val callOne = service.getGenderOne(result)
                callOne.enqueue(object : Callback<ResponseOne> {
                    override fun onFailure(call: Call<ResponseOne>, t: Throwable) {
                        hideProgressBar()
                        Log.i(TAG, t.message)
                        Toast.makeText(applicationContext, "Error, try again later", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ResponseOne>, response: Response<ResponseOne>) {
                        hideProgressBar()
                        val gender = response.body()?.gender
                        Log.i("MyLog", response.body()?.gender)
                        showOneResult(response.body()?.gender!!)
//                        Toast.makeText(applicationContext, gender, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }


    private fun chooseType() {
        AlertDialog.Builder(this@MainActivity)
            .setMessage("Choose type")
            .setCancelable(true)
            .setPositiveButton("One person") { dialog, which ->
                getGender(GendXType.One)
            }
            .setNegativeButton("More then one person") { dialog, which ->
                getGender(GendXType.Many)
            }
            .create()
            .show()
    }


    private fun initObservables() {
        viewModel.event.observe(this, Observer { event ->
            when (event) {
                MainEvents.TakePhoto -> {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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
                    showProgressBar()
                    hideResults()
                    val imageBitmap = data.extras?.get("data") as Bitmap
                    result = convertToBase64(imageBitmap)
                    Log.i(TAG, result)
                    chooseType()
                }
                REQUEST_OPEN_GALLERY -> {
                    showProgressBar()
                    hideResults()
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
                    result = convertToBase64(bitmap)
                    Log.i(TAG, result)
                    chooseType()
                }
            }
        }
    }


    private fun showProgressBar() {
        main_progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        main_progress_bar.visibility = View.INVISIBLE
        main_tv_one_prediction.visibility = View.INVISIBLE
        main__tv_probability.visibility = View.INVISIBLE
    }

    private fun convertToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun hideResults() {
        main_tv_one_prediction.visibility = View.INVISIBLE
        main__tv_probability.visibility = View.INVISIBLE
    }

    private fun showOneResult(prediction: String) {
        main__tv_probability.visibility = View.VISIBLE
        main_tv_one_prediction.visibility = View.VISIBLE
        main_tv_one_prediction.text = prediction
    }
}
