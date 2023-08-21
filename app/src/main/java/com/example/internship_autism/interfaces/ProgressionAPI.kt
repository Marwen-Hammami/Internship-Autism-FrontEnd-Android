package com.example.internship_autism.interfaces

import com.example.internship_autism.models.ProgressionModel
import retrofit2.Call

interface ProgressionAPI {
    fun getData(): Call<List<ProgressionModel>>
}