package com.example.internship_autism.models

open class Card(
    val _id: String? = null,
    val type: String? = null,
    var title: String = "",
    var message: String = "",
    var hint: String? = null
)

data class LectureCard(
    val illustration: String,
) : Card()

data class ArrowExerciseCard(
    val suggestion1: String,
    val illustration1: String,
    val suggestion2: String,
    val illustration2: String,
    val suggestion3: String,
    val illustration3: String,
) : Card()

data class ChoiseExerciseCard(
    val correctIllustration: String,
    val falseIllustration1: String,
    val falseIllustration2: String,
    val falseIllustration3: String,
) : Card()