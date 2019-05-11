package com.example.gendx_android.functionalities.start

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gendx_android.databinding.ActivityStartBinding
import com.example.gendx_android.functionalities.main.MainActivity


class StartActivity : AppCompatActivity() {

    private val REQUEST_CAMERA = 3;

    private lateinit var binding: ActivityStartBinding
    private lateinit var viewModel: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.gendx_android.R.layout.activity_start)

        //region init viewModel
        binding = DataBindingUtil.setContentView(this, com.example.gendx_android.R.layout.activity_start)
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        binding.startViewModel = viewModel
        //endregion

        waitForStart()
    }


    /* Start MainActivity */
    private fun goToMain() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    /* Check for camera permission*/
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        } else {
            goToMain()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToMain()
            } else {
                finish()
            }
        }
    }

    /* Wait for accessing permissions */
    private fun waitForStart() {
        object : CountDownTimer(1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                checkPermissions()
            }
        }.start()
    }
}
