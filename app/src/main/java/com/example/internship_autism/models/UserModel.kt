package com.example.internship_autism.models

import java.util.Date

open class User(
    val _id: String? = null,
    val type: String? = null,
    val firstName: String = "",
    val lastName: String = ""
)

data class Parent(
    val email: String,
    val password: String,
    val sex: String,
    val avatar: String,
    val childsList: List<Child>
): User()

data class Child(
    val birthDay: Date,
    val sex: String,
    val avatar: String,
    val progression: String // id of the progression
) : User()

data class Administrator(
    val email: String,
    val password: String
) : User()

data class SuperAdministrator(
    val email: String,
    val password: String
) : User()