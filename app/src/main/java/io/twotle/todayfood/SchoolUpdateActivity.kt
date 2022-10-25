package io.twotle.todayfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import io.twotle.todayfood.adapter.SchoolSearchRecyclerAdapter
import io.twotle.todayfood.databinding.ActivitySchoolUpdateBinding
import io.twotle.todayfood.model.search.Row
import io.twotle.todayfood.viewmodel.SchoolUpdateViewModel

class SchoolUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySchoolUpdateBinding
    private lateinit var viewModel: SchoolUpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_school_update)
        viewModel = ViewModelProvider(this).get(SchoolUpdateViewModel::class.java)
        binding.viewModel = viewModel

        attachEvent()
    }

    private fun attachEvent() {
        viewModel.requestFailEvent.observe(this) {
            Toast.makeText(this, "요청 실패",Toast.LENGTH_LONG).show()
        }

        viewModel.schoolList.observe(this) {
            val newAdapter = SchoolSearchRecyclerAdapter(it as ArrayList<Row>)
            binding.recyclerSu.adapter = newAdapter
        }
    }
}