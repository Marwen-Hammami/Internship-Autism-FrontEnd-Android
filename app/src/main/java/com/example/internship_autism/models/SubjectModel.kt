package com.example.internship_autism.models

import java.io.Serializable

data class SubjectModel(
    val _id: String? = null,
    val name: String,
    val illustration: String,
    val listLesson: List<LessonModel>
): Serializable //Serializable so i can pass the object in an intent
