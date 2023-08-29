package com.example.internship_autism.ui

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
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.internship_autism.models.Administrator
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.ViewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    //for the login animation
    private var isDetailLayout = false
    lateinit var constraintLayout: ConstraintLayout

    //login elements
    lateinit var loginEmailAddress: EditText
    lateinit var loginPassword: EditText
    lateinit var buttonLogin: AppCompatButton
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_login)

        //for the login animation
        constraintLayout = findViewById(R.id.constraintLayout)

        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        constraintLayout.setOnClickListener {
            if (isDetailLayout)
                swapFrames(R.layout.auth_login) // switch to default layout
            else
                swapFrames(R.layout.auth_login_detail) // switch to detail layout
        }

        //login elements
        loginEmailAddress= findViewById(R.id.loginEmailAddress)
        loginPassword= findViewById(R.id.loginPassword)
        buttonLogin= findViewById(R.id.buttonLogin)

        initViewModel()

        buttonLogin.setOnClickListener{
            //login()
            var user = Administrator(email = loginEmailAddress.text.toString(), password = loginPassword.text.toString())
            logIn(user)
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
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.logInObservable().observe(this, Observer<User?> {
            if(it == null) {
                Toast.makeText(this@MainActivity, "no login...", Toast.LENGTH_LONG).show()
            }else {
                var userLoggedIn = it
                Log.d("MyApp", "User login: "+ it.toString())
                Toast.makeText(this@MainActivity, "login success...", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun logIn(user: Administrator){
        viewModel.logIn(user)
    }

}