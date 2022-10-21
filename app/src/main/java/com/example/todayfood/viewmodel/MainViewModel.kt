package com.example.todayfood.viewmodel;
import android.view.View;
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;

public class MainViewModel:ViewModel(){
    lateinit var mySchoolName: LiveData<String>
    lateinit var foodList : LiveData<String>



    fun goToSchoolUpdateActivity(v: View) {

    }
}
