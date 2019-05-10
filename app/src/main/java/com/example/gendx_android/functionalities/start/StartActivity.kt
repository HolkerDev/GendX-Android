package com.example.gendx_android.functionalities.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.gendx_android.R
import com.example.gendx_android.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var viewModel: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //region init viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        binding.startViewModel = viewModel
        initObservables()
        //endregion
    }

    private fun initObservables() {
        //TODO: Init observable for viewModel event
    }
}
