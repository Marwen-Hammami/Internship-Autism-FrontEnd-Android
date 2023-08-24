package com.example.internship_autism.models

data class SubjectModel(
    val _id: String? = null,
    val name: String,
    val illustration: String,
    val listLesson: List<LessonModel>
)
