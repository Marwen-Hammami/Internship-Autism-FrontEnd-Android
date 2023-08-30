package com.example.internship_autism.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.internship_autism.R
import com.example.internship_autism.models.User

class HomeParent : AppCompatActivity() {
    lateinit var testMessage: TextView

    //current connected parent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_parent)

        //get current parent from intent
        val parent = intent.getSerializableExtra("currentUser") as User

        testMessage = findViewById(R.id.testMessage)

        testMessage.text = parent.toString()
    }
}