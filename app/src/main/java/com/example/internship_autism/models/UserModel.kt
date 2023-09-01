package com.example.internship_autism.models

import com.example.internship_autism.utils.UserType
import java.io.Serializable
import java.util.Date

//open class User
data class User(
    val _id: String? = null,
    val __t: UserType? = null,
    val firstName: String = "",
    val lastName: String = "",
    val email: String? = null,
    val password: String? = null,
    val sex: String? = null,
    val avatar: String? = null,
    val updatePasswordToken: String? = null,
    val updatePasswordExpire: Date? = null,
    val pinCode: String? = null,
    val childsList: List<String>? = null,
    val birthDay: Date? = null,
    val progression: ProgressionModel? = null
): Serializable //Serializable so i can pass the object in an intent
/*
data class Parent(
    val email: String,
    val password: String,
    val sex: String = "",
    val avatar: String = "",
    val childsList: List<Child>? = null
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
 */