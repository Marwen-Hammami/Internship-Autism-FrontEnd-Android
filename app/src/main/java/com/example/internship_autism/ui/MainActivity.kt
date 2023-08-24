package com.example.internship_autism.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internship_autism.R
import com.example.internship_autism.models.ProgressionModel
import com.example.internship_autism.ui.ViewModel.ProgressionViewModel
import com.example.internship_autism.ui.adapters.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    lateinit var txtId: TextView
    lateinit var GetAll: Button
    lateinit var GetOne: Button
    lateinit var recycleview: RecyclerView
    lateinit var RecyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: ProgressionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtId = findViewById(R.id.txtId)
        recycleview = findViewById(R.id.recycleview)

        initRecyclerView()
        initViewModel()


        GetAll = findViewById(R.id.GetAll)
        GetAll.setOnClickListener {
            //getMyData()
            createProgression()
        }

        GetOne = findViewById(R.id.GetOne)
        GetOne.setOnClickListener {
            //getMyData()
            //updateProgression()
            deleteProgression()
        }

    }

    private fun initRecyclerView() {
        recycleview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            RecyclerViewAdapter = RecyclerViewAdapter()
            adapter = RecyclerViewAdapter

        }
    }

    fun initViewModel() {
        //needs to be better separeted
        viewModel = ViewModelProvider(this).get(ProgressionViewModel::class.java)
        viewModel.getProgressionListObservable().observe(this, Observer<List<ProgressionModel>> {
            if(it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            }else {
                RecyclerViewAdapter.progressionList = it.toMutableList()
                RecyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getProgressionList()

        viewModel.getCreateNewProgressionObservable().observe(this, Observer<ProgressionModel?>{
            if (it == null){
                Toast.makeText(this@MainActivity, "failed creation!!...", Toast.LENGTH_LONG).show()
            }else{
                //code in case it get created successfully
            }
        })

        viewModel.getUpdateProgressionObservable().observe(this, Observer<ProgressionModel?>{
            if (it == null){
                Toast.makeText(this@MainActivity, "failed update!!...", Toast.LENGTH_LONG).show()
            }else{
                //code in case it get created successfully
            }
        })

        viewModel.deleteProgressionObservable().observe(this, Observer<Boolean>{
            if (it == false){
                Toast.makeText(this@MainActivity, "failed update!!...", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, "Deleted successfully", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun createProgression(){
        val progression = ProgressionModel(progression = listOf(mapOf("الأبجدية" to 21, "أعداد" to 32)) )
        viewModel.createProgression(progression)
    }

    fun updateProgression(){
        val progression = ProgressionModel(progression = listOf(mapOf("الأبجدية" to 27, "أعداد" to 33)) )
        viewModel.updateProgression("64e721ad224577e1040035db", progression)
        //repopulate the list
        viewModel.getProgressionList()
    }

    fun deleteProgression(){
        viewModel.deleteProgression("64e721ad224577e1040035db")
        //repopulate the list
        viewModel.getProgressionList()
    }

}