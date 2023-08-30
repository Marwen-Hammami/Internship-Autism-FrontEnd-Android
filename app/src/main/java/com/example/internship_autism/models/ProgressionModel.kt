package com.example.internship_autism.models

import java.io.Serializable

data class ProgressionModel(
    val _id: String? = null,
    var progression: List<Map<String, Int>>,
): Serializable //Serializable so i can pass the object in an intent
