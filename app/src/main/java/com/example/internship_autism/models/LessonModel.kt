package com.example.internship_autism.models

import java.io.Serializable

data class LessonModel(
    val _id: String? = null,
    val name: String,
    val listCards: List<String>,
    val illustration: String
): Serializable //Serializable so i can pass the object in an intent
