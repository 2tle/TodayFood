package io.twotle.todayfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import io.twotle.todayfood.databinding.ActivityMainBinding
import io.twotle.todayfood.viewmodel.MainViewModel
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        MobileAds.initialize(this) {}

        attachSingleLiveEvent()

    }

    private fun attachSingleLiveEvent() {
        viewModel.schoolUpdateEvent.observe(this) {
            startActivity(Intent(this, io.twotle.todayfood.SchoolUpdateActivity::class.java))
        }
    }
}