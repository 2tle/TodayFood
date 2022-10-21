package com.example.todayfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.todayfood.databinding.ActivityMainBinding
import com.example.todayfood.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainViewModel()
        binding.viewModel = viewModel


        attachSingleLiveEvent()


    }

    private fun attachSingleLiveEvent() {
        viewModel.schoolUpdateEvent.observe(this) {
            startActivity(Intent(this, SchoolUpdateActivity::class.java))
        }
    }
}