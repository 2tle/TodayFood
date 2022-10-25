package io.twotle.todayfood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import io.twotle.todayfood.MainActivity
import io.twotle.todayfood.R
import io.twotle.todayfood.databinding.ItemSuBinding
import io.twotle.todayfood.model.search.Row
import io.twotle.todayfood.repository.sf.SharedPreferences

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
                binding.root.context.startActivity(Intent(binding.root.context, io.twotle.todayfood.MainActivity::class.java))
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