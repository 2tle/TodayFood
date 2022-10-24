package com.example.todayfood.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayfood.event.SingleLiveEvent
import com.example.todayfood.model.search.Row
import com.example.todayfood.repository.retrofit.NeisApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SchoolUpdateViewModel : ViewModel() {
    val requestFailEvent = SingleLiveEvent<Any>()

    var schoolList: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()


    fun query(school: String) {
        val res = runBlocking {
            NeisApi.getApiService().getSchoolInfo(schoolName = school)
        }
        if(!res.isSuccessful) {
            requestFailEvent.call()
        }
        else if(res.body() == null) {
            requestFailEvent.call()
        }
        else {
            try {
                schoolList.value = res.body()!!.schoolInfo[1].row
            } catch(e: Exception) {
                requestFailEvent.call()
            }

        }

        //Log.d(">",res.body()!!.schoolInfo[1].row[0].SCHUL_NM)
    }




}