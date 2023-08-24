package com.example.internship_autism.models

data class LessonModel(
    val _id: String? = null,
    val name: String,
    val illustration: String,
    val listCards: List<Card>
)
