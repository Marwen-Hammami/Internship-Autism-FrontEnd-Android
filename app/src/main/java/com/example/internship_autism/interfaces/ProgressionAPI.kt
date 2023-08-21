package com.example.internship_autism.interfaces

import com.example.internship_autism.models.ProgressionModel
import retrofit2.Call
import retrofit2.http.GET

interface ProgressionAPI {
    @GET("progressions")
    fun getData(): Call<List<ProgressionModel>>
}