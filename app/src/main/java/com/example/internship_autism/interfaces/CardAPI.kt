package com.example.internship_autism.interfaces

import com.example.internship_autism.models.Card
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CardAPI {
    @GET("cards")
    fun getCards(): Call<List<Card>>

    @GET("cards/{id}")
    fun getCard(@Path("id") id: String): Call<Card>

    @POST("cards")
    fun createCard(@Body params: Card): Call<Card>

    @PUT("cards/{id}")
    fun updateCard(@Path("id") id: String, @Body params: Card): Call<Card>

    @DELETE("cards/{id}")
    fun deleteCard(@Path("id") id: String): Call<Void>
}