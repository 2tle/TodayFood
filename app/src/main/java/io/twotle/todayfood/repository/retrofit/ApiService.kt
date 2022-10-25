package io.twotle.todayfood.repository.retrofit

import io.twotle.todayfood.model.meal.MealServiceModel
import io.twotle.todayfood.model.search.SchoolSearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("mealServiceDietInfo")
    suspend fun getMealData(
        @Query("type") type: String = "json",
        @Query("KEY") key: String = "f77b92721a7741fab05c39b9e9e9371c",
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("MLSV_YMD") date: String
    ): Response<MealServiceModel>

    @GET("schoolInfo")
    suspend fun getSchoolInfo(
        @Query("type") type: String = "json",
        @Query("KEY") key: String = "f77b92721a7741fab05c39b9e9e9371c",
        @Query("SCHUL_NM") schoolName: String
    ): Response<SchoolSearchModel>

}