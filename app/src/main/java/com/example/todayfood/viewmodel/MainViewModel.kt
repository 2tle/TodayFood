package com.example.todayfood.viewmodel;
import android.util.Log
import android.view.View;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.todayfood.event.SingleLiveEvent
import com.example.todayfood.repository.retrofit.NeisApi
import com.example.todayfood.repository.sf.SharedPreferences
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

public class MainViewModel:ViewModel(){
    val mySchoolName: MutableLiveData<String> by lazy {
        MutableLiveData<String>(SharedPreferences.prefs.getString("SchoolName","학교없음"))
    }
    val foodList : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        if(SharedPreferences.prefs.getString("SchoolName","학교없음") != "학교없음") {
            val res = runBlocking {
                NeisApi.getApiService().getMealData(officeCode = SharedPreferences.prefs.getString("OfficeID","0"), schoolCode = SharedPreferences.prefs.getString("SchoolID","0"), date = LocalDate.now().toString().replace("-",""))
            }
            Log.d(">>>",SharedPreferences.prefs.getString("OfficeID","0") + SharedPreferences.prefs.getString("SchoolID","0"),)
            if(res.body()!!.mealServiceDietInfo != null) {
                foodList.value = res.body()!!.mealServiceDietInfo[1].row[0].DDISH_NM.replace("<br>","\n").replace("<br/>","\n")
            } else {
                foodList.value = "급식 없음";
            }


        }


    }

    val schoolUpdateEvent = SingleLiveEvent<Any>()


    fun goToSchoolUpdateActivity(v: View) {
        schoolUpdateEvent.call()
    }
}
