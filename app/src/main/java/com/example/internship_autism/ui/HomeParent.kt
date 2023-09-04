package com.example.internship_autism.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.internship_autism.R
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.ViewModel.UserViewModel
import com.example.internship_autism.utils.UserType

class HomeParent : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var listChilds: MutableList<User>

    //Avatar and Name of Users
    lateinit var textVieParent: TextView
    lateinit var imageViewParent: ImageView
    lateinit var textViewChild1: TextView
    lateinit var imageViewChild1: ImageView
    lateinit var textViewChild2: TextView
    lateinit var imageViewChild2: ImageView
    lateinit var textViewChild3: TextView
    lateinit var imageViewChild3: ImageView

    //On Click User
    lateinit var cardParent: ConstraintLayout
    lateinit var cardChild1: ConstraintLayout
    lateinit var cardChild2: ConstraintLayout
    lateinit var cardChild3: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_parent)

        hideBottomNavigationBar()

        //get current parent from intent
        val parent = intent.getSerializableExtra("currentUser") as User

        findElementsById()

        //init User viewModel
        initViewModel()

        getChilds(parent.childsList)

        initParent(parent)

        UsersOnClickRedirect(parent)

    }

    fun hideBottomNavigationBar() {
        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun findElementsById() {
        //Avatar and Name of Users
        textVieParent = findViewById(R.id.textVieParent)
        imageViewParent = findViewById(R.id.imageViewParent)
        textViewChild1 = findViewById(R.id.textViewChild1)
        imageViewChild1 = findViewById(R.id.imageViewChild1)
        textViewChild2 = findViewById(R.id.textViewChild2)
        imageViewChild2 = findViewById(R.id.imageViewChild2)
        textViewChild3 = findViewById(R.id.textViewChild3)
        imageViewChild3 = findViewById(R.id.imageViewChild3)

        //On Click User
        cardParent = findViewById(R.id.cardParent)
        cardChild1 = findViewById(R.id.cardChild1)
        cardChild2 = findViewById(R.id.cardChild2)
        cardChild3 = findViewById(R.id.cardChild3)
    }

    fun getChilds(list: List<String>?){
        if (list != null) {
            list.forEach { item ->
                userViewModel.getUser(item)
            }
        }
    }

    fun initViewModel() {
        listChilds = mutableListOf()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUserObservable().observe(this, Observer<User?> {
            if(it == null) {
                Log.d("MyApp", "Parent have no Childs1")
            }else {
                listChilds.add(it)
                textViewChild1.text = it.firstName + " " + it.lastName
                var img = getAvatar(it.avatar)
                imageViewChild1.setImageResource(img)
            }
        })
        userViewModel.getUserObservable2().observe(this, Observer<User?> {
            if(it == null) {
                Log.d("MyApp", "Parent have no Childs2")
            }else {
                listChilds.add(it)
                textViewChild2.text = it.firstName + " " + it.lastName
                var img = getAvatar(it.avatar)
                imageViewChild2.setImageResource(img)
            }
        })
        userViewModel.getUserObservable3().observe(this, Observer<User?> {
            if(it == null) {
                Log.d("MyApp", "Parent have no Childs3")
            }else {
                listChilds.add(it)
                textViewChild3.text = it.firstName + " " + it.lastName
                var img = getAvatar(it.avatar)
                imageViewChild3.setImageResource(img)
            }
        })
    }

    fun initParent(parent: User) {
        textVieParent.text = parent.firstName + " " + parent.lastName
        var img = getAvatar(parent.avatar)
        imageViewParent.setImageResource(img)
    }

    //return the correct picture switch User.avatar
    fun getAvatar(avatarName: String?): Int {
        return when (avatarName) {
            "male01" -> R.drawable.male01
            "male02" -> R.drawable.male02
            "male03" -> R.drawable.male03
            "male04" -> R.drawable.male04
            "male05" -> R.drawable.male05
            "male11" -> R.drawable.male11
            "male12" -> R.drawable.male12
            "male13" -> R.drawable.male13
            "male14" -> R.drawable.male14
            "male15" -> R.drawable.male15
            "female01" -> R.drawable.female01
            "female02" -> R.drawable.female02
            "female03" -> R.drawable.female03
            "female04" -> R.drawable.female04
            "female05" -> R.drawable.female05
            "female11" -> R.drawable.female11
            "female12" -> R.drawable.female12
            "female13" -> R.drawable.female13
            "female14" -> R.drawable.female14
            "female15" -> R.drawable.female15
            else -> R.drawable.female01
        }
    }

    fun UsersOnClickRedirect(parent: User) {
        cardParent.setOnClickListener{
            Toast.makeText(this@HomeParent, "Parent Clicked", Toast.LENGTH_LONG).show()
        }
        cardChild1.setOnClickListener{
            if (listChilds.size > 0) {
                val intent = Intent(this@HomeParent, Subjects::class.java)
                    .putExtra("currentUser", parent)
                    .putExtra("currentChild", listChilds[0])
                startActivity(intent)
            }else{
                //after pin code
                Toast.makeText(this@HomeParent, "Go To ADD Child Form", Toast.LENGTH_LONG).show()
            }
        }
        cardChild2.setOnClickListener{
            if (listChilds.size > 1) {
                val intent = Intent(this@HomeParent, Subjects::class.java)
                    .putExtra("currentUser", parent)
                    .putExtra("currentChild", listChilds[1])
                startActivity(intent)
            }else{
                //after pin code
                Toast.makeText(this@HomeParent, "Go To ADD Child Form", Toast.LENGTH_LONG).show()
            }
        }
        cardChild3.setOnClickListener{
            if (listChilds.size > 2) {
                val intent = Intent(this@HomeParent, Subjects::class.java)
                    .putExtra("currentUser", parent)
                    .putExtra("currentChild", listChilds[2])
                startActivity(intent)
            }else{
                //after pin code
                Toast.makeText(this@HomeParent, "Go To ADD Child Form", Toast.LENGTH_LONG).show()
            }
        }
    }

}