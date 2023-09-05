package com.example.internship_autism.models

import java.io.Serializable

data class SubjectModel(
    val _id: String? = null,
    val name: String,
    val listLessons: List<LessonModel>,
    val illustration: String
): Serializable //Serializable so i can pass the object in an intent
