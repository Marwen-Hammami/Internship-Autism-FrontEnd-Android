package com.example.internship_autism.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
    lateinit var nextButton: AppCompatButton
    lateinit var previousButton: AppCompatButton

    //Choise Exercice Card
    lateinit var TitleChoise: TextView
    lateinit var illustration_top_left: ImageView
    lateinit var illustration_top_right: ImageView
    lateinit var illustration_bottom_left: ImageView
    lateinit var illustration_bottom_right: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideBottomNavigationBar()

        //get current elements from intent
        val child = intent.getSerializableExtra("child") as User
        val listCards = intent.getSerializableExtra("listCards") as MutableList<Card>

        displayCard(listCards)
    }

    fun displayCard(listCards: MutableList<Card>) {
        // 0 < 3 --- 1 < 3 --- 2 < 3
        if (cardNumber < listCards.size) {
            var current = listCards[cardNumber]
            when (current.__t) {
                "Lecture" -> displayLecture(current, listCards)
                "ChoiseExercise" -> displayChoiseExercice(current, listCards)
                "ArrowExercise" -> displayArrowExercice(current, listCards)
            }

        } else {
            finish()
        }
    }

    fun hideBottomNavigationBar() {
        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun displayLecture(current: Card, listCards: MutableList<Card>) {
        setContentView(R.layout.card_lecture)

        findElementsByIdLecture()

        populateLectureTemplate(current)

        onClickNext(listCards)
    }

    fun findElementsByIdLecture() {
        Title = findViewById(R.id.Title)
        illustration = findViewById(R.id.illustration)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)
    }

    fun populateLectureTemplate(current: Card) {
        Title.text = current.title
        val bitmap = current.illustration?.let { convertBase64ImageToBitmap(it) }
        illustration.setImageBitmap(bitmap)
    }

    fun onClickNext(listCards: MutableList<Card>) {
        nextButton.setOnClickListener {
            if (cardNumber == listCards.size - 1) {
                finish()
            } else {
                cardNumber++
                displayCard(listCards)
            }
        }
    }

    fun displayChoiseExercice(current: Card, listCards: MutableList<Card>) {
        //variables
        var correctImageView : ImageView? = null

        setContentView(R.layout.card_choise_exercice)

        findElementsByIdChoiseExercice()

        correctImageView = populateChoiseExerciceTemplate(current, listCards)

        onClickNext(listCards)

        Log.d("MyApp", "illustration_top_left " + illustration_top_left.equals(correctImageView))
        Log.d("MyApp", "illustration_top_right " + illustration_top_right.equals(correctImageView))
        Log.d("MyApp", "illustration_bottom_left " + illustration_bottom_left.equals(correctImageView))
        Log.d("MyApp", "illustration_bottom_right " + illustration_bottom_right.equals(correctImageView))
    }

    fun findElementsByIdChoiseExercice() {
        TitleChoise = findViewById(R.id.TitleChoise)
        illustration_top_left = findViewById(R.id.illustration_top_left)
        illustration_top_right = findViewById(R.id.illustration_top_right)
        illustration_bottom_left = findViewById(R.id.illustration_bottom_left)
        illustration_bottom_right = findViewById(R.id.illustration_bottom_right)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)
    }

    fun populateChoiseExerciceTemplate(current: Card, listCards: MutableList<Card>): ImageView? {
        TitleChoise.text = current.title

        //Convert the card images to bitmap
        val bitmap1 = current.correctIllustration?.let { convertBase64ImageToBitmap(it) }
        val bitmap2 = current.falseIllustration1?.let { convertBase64ImageToBitmap(it) }
        val bitmap3 = current.falseIllustration2?.let { convertBase64ImageToBitmap(it) }
        val bitmap4 = current.falseIllustration3?.let { convertBase64ImageToBitmap(it) }

        val imageBitmaps = mutableListOf(bitmap1, bitmap2, bitmap3, bitmap4)

        //List of ImageViews
        val imageViews = mutableListOf(illustration_top_left, illustration_top_right, illustration_bottom_left, illustration_bottom_right)

        imageViews.shuffle()
        var correctImageViewVar: ImageView? = null

        // Assign images to the shuffled ImageViews
        for (i in 0 until imageViews.size) {
            val imageView = imageViews[i]
            val imageBitmap = imageBitmaps[i]
            imageView.setImageBitmap(imageBitmap)

            //Store the correct answer in a different variablme for later check
            if (imageBitmap == bitmap1) {
                correctImageViewVar = imageView
            }

            imageView.setOnClickListener {
                if (imageView.equals(correctImageViewVar)) {
                    if (cardNumber == listCards.size - 1) {
                        finish()
                    } else {
                        cardNumber++
                        displayCard(listCards)
                    }
                } else {
                    //hint
                    Toast.makeText(this@Cards, "اجابة خاطئة", Toast.LENGTH_LONG).show()
                }
            }
        }

        return correctImageViewVar

    }


    fun displayArrowExercice(current: Card, listCards: MutableList<Card>) {
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