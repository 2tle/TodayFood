package com.example.todayfood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.todayfood.MainActivity
import com.example.todayfood.R
import com.example.todayfood.databinding.ItemSuBinding
import com.example.todayfood.model.search.Row
import com.example.todayfood.repository.sf.SharedPreferences

class SchoolSearchRecyclerAdapter(var data: ArrayList<Row>): RecyclerView.Adapter<SchoolSearchRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Row) {
            binding.itemSchoolName.text = data.SCHUL_NM
            binding.itemSchoolAddress.text = data.ORG_RDNMA
            binding.itemClickable.setOnClickListener {
                SharedPreferences.prefs.setString("SchoolID",data.SD_SCHUL_CODE);
                SharedPreferences.prefs.setString("OfficeID",data.ATPT_OFCDC_SC_CODE);
                SharedPreferences.prefs.setString("SchoolName",data.SCHUL_NM);
                //binding.root.startActivity(Intent(binding.root.context, MainActivity::class.java))
                binding.root.context.startActivity(Intent(binding.root.context, MainActivity::class.java))
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.get(position))
    }




}