package com.example.internship_autism.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.internship_autism.R
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.ViewModel.UserViewModel
import com.example.internship_autism.utils.UserType

class MainActivity : AppCompatActivity() {
    //for the login animation
    private var isDetailLayout = false
    lateinit var constraintLayout: ConstraintLayout

    //login elements
    lateinit var loginEmailAddress: EditText
    lateinit var loginPassword: EditText
    lateinit var buttonLogin: AppCompatButton
    lateinit var userViewModel: UserViewModel
    lateinit var errorMessage: TextView
    lateinit var userResponse: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_login)

        hideBottomNavigationBar()

        findElementsById()

        logInScreenTransitionAnimation()

        initViewModel()

        buttonLogin.setOnClickListener{
            var user = User(email = loginEmailAddress.text.toString(), password = loginPassword.text.toString())
            logIn(user)
        }
    }

    fun hideBottomNavigationBar() {
        //hide the android navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun findElementsById() {
        //For the login animation
        constraintLayout = findViewById(R.id.constraintLayout)

        //login elements
        loginEmailAddress= findViewById(R.id.loginEmailAddress)
        loginPassword= findViewById(R.id.loginPassword)
        buttonLogin= findViewById(R.id.buttonLogin)
        errorMessage= findViewById(R.id.errorMessage)
    }

    fun logInScreenTransitionAnimation() {
        constraintLayout.setOnClickListener {
            if (isDetailLayout)
                swapFrames(R.layout.auth_login) // switch to default layout
            else
                swapFrames(R.layout.auth_login_detail) // switch to detail layout
        }
    }


    //for the animatin
    private fun swapFrames(layoutId: Int){

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, layoutId)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraintLayout, transition)

        constraintSet.applyTo(constraintLayout)

        isDetailLayout = !isDetailLayout
    }

    fun initViewModel() {
        //needs to be better separeted
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.logInObservable().observe(this, Observer<User?> {
            if(it == null) {
                errorMessage.visibility = View.VISIBLE
            }else {
                errorMessage.visibility = View.GONE
                //Log.d("MyApp", "User"+ it)
                //redirect switch role
                when (it.__t) {
                    UserType.Parent -> {
                        val intent = Intent(this@MainActivity, HomeParent::class.java)
                            .putExtra("currentUser", it)
                        startActivity(intent)
                    }
                    UserType.Administrator -> {
                        val intent = Intent(this@MainActivity, HomeAdministrator::class.java)
                            .putExtra("currentUser", it)
                        startActivity(intent)
                    }
                    UserType.SuperAdministrator ->{
                        val intent = Intent(this@MainActivity, HomeSuperAdministrator::class.java)
                            .putExtra("currentUser", it)
                        startActivity(intent)
                    }
                    else -> {}
                }
            }
        })
    }

    fun logIn(user: User){
        userViewModel.logIn(user)
    }

}