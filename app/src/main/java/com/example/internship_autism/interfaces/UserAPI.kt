package com.example.internship_autism.interfaces

import com.example.internship_autism.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserAPI {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: String): Call<User>

    @POST("users")
    fun createUser(@Body params: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: String, @Body params: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>

    @POST("users/login")
    fun logIn(@Body user: User): Call<User>

    @PUT("users/updatepassword")
    fun updatePassword(@Body params: User): Call<User>
}