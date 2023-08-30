package com.example.internship_autism.models

import java.io.Serializable

//open class Card
data class Card(
    val _id: String? = null,
    val type: String? = null,
    var title: String = "",
    var message: String = "",
    var hint: String? = null,
    val illustration: String? = null,
    val suggestion1: String? = null,
    val illustration1: String? = null,
    val suggestion2: String? = null,
    val illustration2: String? = null,
    val suggestion3: String? = null,
    val illustration3: String? = null,
    val correctIllustration: String? = null,
    val falseIllustration1: String? = null,
    val falseIllustration2: String? = null,
    val falseIllustration3: String? = null,
): Serializable //Serializable so i can pass the object in an intent

/*
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

 */