package com.example.internship_autism.interfaces

import com.example.internship_autism.models.ProgressionModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProgressionAPI {
    @GET("progressions")
    fun getProgressions(): Call<List<ProgressionModel>>

    @GET("progressions/{id}")
    fun getProgression(@Path("id") id: String): Call<ProgressionModel>

    @POST("progressions")
    fun createProgression(@Body params: ProgressionModel): Call<ProgressionModel>

    @PUT("progressions/{id}")
    fun updateProgression(@Path("id") id: String, @Body params: ProgressionModel): Call<ProgressionModel>

    @DELETE("progressions/{id}")
    fun deleteProgression(@Path("id") id: String): Call<Void>
}