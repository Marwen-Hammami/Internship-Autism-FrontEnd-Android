package com.example.internship_autism.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.internship_autism.R
import com.example.internship_autism.models.Card
import com.example.internship_autism.models.User

class Cards : AppCompatActivity()   {
    var cardNumber: Int = 0

    //Lecture Card
    lateinit var Title: TextView
    lateinit var illustration: ImageView
    lateinit var next: AppCompatButton
    lateinit var previous: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideBottomNavigationBar()

        //get current elements from intent
        val child = intent.getSerializableExtra("child") as User
        val listCards = intent.getSerializableExtra("listCards") as MutableList<Card>

        if (cardNumber <= listCards.size) {
            var current = listCards[cardNumber]
            when (current.__t) {
                "Lecture" -> displayLecture(current)
                "ChoiseExercise" -> displayChoiseExercice(current)
                "ArrowExercise" -> displayArrowExercice(current)
            }

        }

    }

    fun hideBottomNavigationBar() {
        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun displayLecture(current: Card) {
        setContentView(R.layout.card_lecture)

        findElementsByIdLecture()

        populateLectureTemplate(current)


    }

    fun findElementsByIdLecture() {
        Title = findViewById(R.id.Title)
        illustration = findViewById(R.id.illustration)
        next = findViewById(R.id.next)
        previous = findViewById(R.id.previous)
    }

    fun populateLectureTemplate(current: Card) {
        Title.text = current.title
        val bitmap = current.illustration?.let { convertBase64ImageToBitmap(it) }
        illustration.setImageBitmap(bitmap)
    }

    fun displayChoiseExercice(current: Card) {
        TODO("Not yet implemented")
    }


    fun displayArrowExercice(current: Card) {
        Log.w("MyApp", "Arrow Exercice TODO" )
    }

    fun convertBase64ImageToBitmap(illustration: String): Bitmap {
        // Remove the Base64 prefix (data:image/png;base64,)
        val parts = illustration.split(",")
        val base64ImageWithoutPrefix = if (parts.size > 1) parts[1] else illustration
        //Decode Base64 Image
        val imageBytes = Base64.decode(base64ImageWithoutPrefix, Base64.DEFAULT)
        // Create a Bitmap from the byte array
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

}