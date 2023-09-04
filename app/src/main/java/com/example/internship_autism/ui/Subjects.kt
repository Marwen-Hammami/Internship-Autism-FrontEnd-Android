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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.internship_autism.R
import com.example.internship_autism.models.SubjectModel
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.ViewModel.SubjectViewModel

class Subjects : AppCompatActivity() {
    //Illustrations and names of subjects
    lateinit var imageViewSubject1: ImageView
    lateinit var textViewSubject1: TextView
    lateinit var imageViewSubject2: ImageView
    lateinit var textViewSubject2: TextView
    lateinit var imageViewSubject3: ImageView
    lateinit var textViewSubject3: TextView
    lateinit var imageViewSubject4: ImageView
    lateinit var textViewSubject4: TextView

    //Cards For On Click Redirection
    lateinit var cardSubject1: ConstraintLayout
    lateinit var cardSubject2: ConstraintLayout
    lateinit var cardSubject3: ConstraintLayout
    lateinit var cardSubject4: ConstraintLayout

    //For Subjects ViwModel
    lateinit var subjectViewModel: SubjectViewModel
    lateinit var listSubjects: MutableList<SubjectModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subjects)

        //get current parent from intent
        val parent = intent.getSerializableExtra("currentUser") as User
        val child = intent.getSerializableExtra("currentChild") as User

        var progression = getChildProgression(child)

        hideBottomNavigationBar()

        findElementsById()

        //init Subjects viewModel
        initViewModel()

        subjectViewModel.getSubjectList()

        populateSubjectsCards()

        subjectOnClickRedirection()

    }

    fun getChildProgression(child: User): Any {
        //Map of the child progression
        val progression = child.progression?.progression?.get(0)
        //cast the nullable Map to a non nullable Map, so i can acces an element by its Key
        val newMap: Map<String, Int> = progression as? Map<String, Int> ?: emptyMap()
        return newMap
//        try {
//            Log.d("MyApp", "-------------------------------")
//            Log.d("MyApp", "try"+newMap["الأبجدية"])
//        }catch (e: Exception){
//            Log.d("MyApp", "erreur catched "+ e.message)
//        }
    }

    fun hideBottomNavigationBar() {
        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun findElementsById() {
        //Illustrations and names of subjects
        imageViewSubject1 = findViewById(R.id.imageViewSubject1)
        textViewSubject1 = findViewById(R.id.textViewSubject1)
        imageViewSubject2 = findViewById(R.id.imageViewSubject2)
        textViewSubject2 = findViewById(R.id.textViewSubject2)
        imageViewSubject3 = findViewById(R.id.imageViewSubject3)
        textViewSubject3 = findViewById(R.id.textViewSubject3)
        imageViewSubject4 = findViewById(R.id.imageViewSubject4)
        textViewSubject4 = findViewById(R.id.textViewSubject4)

        //Cards For On Click Redirection
        cardSubject1 = findViewById(R.id.cardSubject1)
        cardSubject2 = findViewById(R.id.cardSubject2)
        cardSubject3 = findViewById(R.id.cardSubject3)
        cardSubject4 = findViewById(R.id.cardSubject4)
    }

    fun subjectOnClickRedirection() {
        //to do
    }

    fun initViewModel() {
        listSubjects = mutableListOf()
        subjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        subjectViewModel.getSubjectListObservable().observe(this, Observer<List<SubjectModel>> {
            if(it == null) {
                Log.w("MyApp", "There is no Subjects")
            }else {
                listSubjects = it.toMutableList()
                populateSubjectsCards()
            }
        })
    }

    fun populateSubjectsCards() {
        if (listSubjects.isNotEmpty()){
            Log.w("MyApp", "subs"+ listSubjects)
            Log.w("MyApp", "illustration 0"+ listSubjects[0].illustration)
            try {
                textViewSubject1.text = listSubjects[0].name
                val bitmap = convertBase64ImageToBitmap(listSubjects[0].illustration)
                imageViewSubject1.setImageBitmap(bitmap)

                textViewSubject2.text = listSubjects[1].name
                val bitmap2 = convertBase64ImageToBitmap(listSubjects[1].illustration)
                imageViewSubject2.setImageBitmap(bitmap2)

                textViewSubject3.text = listSubjects[2].name
                val bitmap3 = convertBase64ImageToBitmap(listSubjects[2].illustration)
                imageViewSubject3.setImageBitmap(bitmap3)

                textViewSubject4.text = listSubjects[3].name
                val bitmap4 = convertBase64ImageToBitmap(listSubjects[3].illustration)
                imageViewSubject4.setImageBitmap(bitmap4)
            }catch (e: IndexOutOfBoundsException){
                Log.d("MyApp", "End Of Subjects")
            }
        }
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