package com.example.internship_autism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.internship_autism.interfaces.ProgressionAPI
import com.example.internship_autism.models.ProgressionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.1.16:3000/api/"
class MainActivity : AppCompatActivity() {
    lateinit var txtId: TextView
    lateinit var callAPIButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtId = findViewById(R.id.txtId)

        callAPIButton = findViewById(R.id.callAPI)
        callAPIButton.setOnClickListener {
            getMyData()
        }

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProgressionAPI::class.java)

        val retorfitData = retrofitBuilder.getData()

        retorfitData.enqueue(object : Callback<List<ProgressionModel>?> {
            override fun onResponse(
                call: Call<List<ProgressionModel>?>,
                response: Response<List<ProgressionModel>?>
            ) {
                Log.d("resbod", response.body().toString())
                val responseBody = response.body()!!


                val myStringBuilder = StringBuilder()
                for (myData in responseBody) {
                    myStringBuilder.append(myData._id)
                    myStringBuilder.append("\n")
                }
                txtId.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<ProgressionModel>?>?, t: Throwable?) {
                if (t != null) {
                    Log.d("MainActivityError", "onFailure: "+ t.message.toString())
                }
            }
        })
    }
}