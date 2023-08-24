package com.example.internship_autism.interfaces

import com.example.internship_autism.models.LessonModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LessonAPI {
    @GET("lessons")
    fun getLessons(): Call<List<LessonModel>>

    @GET("lessons/{id}")
    fun getLesson(@Path("id") id: String): Call<LessonModel>

    @POST("lessons")
    fun createLesson(@Body params: LessonModel): Call<LessonModel>

    @PUT("lessons/{id}")
    fun updateLesson(@Path("id") id: String, @Body params: LessonModel): Call<LessonModel>

    @DELETE("lessons/{id}")
    fun deleteLesson(@Path("id") id: String): Call<Void>
}