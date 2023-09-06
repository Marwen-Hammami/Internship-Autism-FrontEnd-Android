package com.example.internship_autism.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internship_autism.R
import com.example.internship_autism.models.Card
import com.example.internship_autism.models.LessonModel
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.RecyclerView.LessonRecyclerView
import com.example.internship_autism.ui.ViewModel.CardViewModel

class Lessons : AppCompatActivity()  {
    //For Lesson Display On a Grid
    private var recyclerView: RecyclerView? = null
    private var recyclerViewLessonsAdapter: LessonRecyclerView? = null

    //List of cards of a lesson
    lateinit var cardViewModel: CardViewModel
    var listCards: MutableList<Card> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lessons)

        hideBottomNavigationBar()

        initViewModel()

        //get current elements from intent
        val parent = intent.getSerializableExtra("currentUser") as User
        val child = intent.getSerializableExtra("currentChild") as User
        val listLessons = intent.getSerializableExtra("lessons") as MutableList<LessonModel>

        var progression = getChildProgression(child)

        findElementsById()

        initGridView(listLessons)

//        Log.w("MyApp", "par"+parent)
//        Log.w("MyApp", "child"+child)
//        Log.w("MyApp", "lessons"+listLessons)
//        Log.w("MyApp", "prog"+progression)

        recyclerViewLessonsAdapter?.setOnItemClickListener { position ->
            //populate listCards
            getCards(listLessons[position].listCards)
        }


        checkIfListCardsHaveBeenPopulated()
    }

    fun checkIfListCardsHaveBeenPopulated() {
        val handler = Handler()
        val delayMillis = 1000 // Set the delay in milliseconds

        val checkConditionRunnable = object : Runnable {
            override fun run() {
                val conditionToCheck = listCards.size == 0
                // Check the condition
                if (conditionToCheck) {
                    //ListCards STill Empty
                    // Re-post the Runnable to run it again after a delay
                    handler.postDelayed(this, delayMillis.toLong())
                } else {
                    // Condition is not met, stop checking
                    handler.removeCallbacks(this)
                    //Call Card Activity
                    //
                    Log.w("MyApp", "False - "+listCards)
                }
            }
        }
        // Start the periodic check
        handler.postDelayed(checkConditionRunnable, delayMillis.toLong())
    }

    fun initViewModel() {
        cardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        cardViewModel.getCardObservable().observe(this, Observer<Card?> {
            if(it == null) {
                Log.d("MyApp", "No card 1" )
            }else {
                listCards.add(it)
            }
        })
        cardViewModel.getCardObservable2().observe(this, Observer<Card?> {
            if(it == null) {
                Log.d("MyApp", "No card 2" )
            }else {
                listCards.add(it)
            }
        })
        cardViewModel.getCardObservable3().observe(this, Observer<Card?> {
            if(it == null) {
                Log.d("MyApp", "No card 3" )
            }else {
                listCards.add(it)
            }
        })
        cardViewModel.getCardObservable4().observe(this, Observer<Card?> {
            if(it == null) {
                Log.d("MyApp", "No card 4" )
            }else {
                listCards.add(it)
            }
        })
        cardViewModel.getCardObservable5().observe(this, Observer<Card?> {
            if(it == null) {
                Log.d("MyApp", "No card 5" )
            }else {
                listCards.add(it)
            }
        })
    }

    fun hideBottomNavigationBar() {
        //hide the navigation bar for more space
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
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

    fun findElementsById() {
        //For Lesson Display On a Grid
        recyclerView = findViewById<View>(R.id.rvLessonsList) as RecyclerView
    }

    fun initGridView(listLessons: MutableList<LessonModel>) {
        recyclerViewLessonsAdapter = LessonRecyclerView(this@Lessons, listLessons)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 4)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewLessonsAdapter

        recyclerViewLessonsAdapter!!.notifyDataSetChanged()
    }

    fun getCards(list: List<String>?){
        if (list != null) {
            list.forEach { item ->
                cardViewModel.getCard(item)
            }
            cardViewModel.clearCounter()
        }
    }

}