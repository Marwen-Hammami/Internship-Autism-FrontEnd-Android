package com.example.internship_autism.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internship_autism.R
import com.example.internship_autism.models.LessonModel
import com.example.internship_autism.models.User
import com.example.internship_autism.ui.RecyclerView.LessonRecyclerView

class Lessons : AppCompatActivity()  {
    //For Lesson Display On a Grid
    private var recyclerView: RecyclerView? = null
    private var recyclerViewLessonsAdapter: LessonRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lessons)

        hideBottomNavigationBar()

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

}