package com.example.internship_autism.interfaces

import com.example.internship_autism.models.SubjectModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SubjectAPI {
    @GET("subjects")
    fun getSubjects(): Call<List<SubjectModel>>

    @GET("subjects/{id}")
    fun getSubject(@Path("id") id: String): Call<SubjectModel>

    @POST("subjects")
    fun createSubject(@Body params: SubjectModel): Call<SubjectModel>

    @PUT("subjects/{id}")
    fun updateSubject(@Path("id") id: String, @Body params: SubjectModel): Call<SubjectModel>

    @DELETE("subjects/{id}")
    fun deleteSubject(@Path("id") id: String): Call<Void>
}