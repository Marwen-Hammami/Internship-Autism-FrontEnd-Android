package com.example.internship_autism.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internship_autism.interfaces.LessonAPI
import com.example.internship_autism.models.LessonModel
import com.example.internship_autism.utils.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonViewModel:  ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<LessonModel>>
    lateinit var getLiveData: MutableLiveData<LessonModel>
    lateinit var createLiveData: MutableLiveData<LessonModel?>
    lateinit var updateLiveData: MutableLiveData<LessonModel?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        getLiveData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getLessonListObservable(): MutableLiveData<List<LessonModel>> {
        return recyclerListData
    }

    fun getLessonObservable(): MutableLiveData<LessonModel> {
        return getLiveData
    }

    fun getCreateNewLessonObservable(): MutableLiveData<LessonModel?> {
        return createLiveData
    }

    fun getUpdateLessonObservable(): MutableLiveData<LessonModel?> {
        return updateLiveData
    }

    fun deleteLessonObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getLessonList() {
        val retroInstance = RetroInstance.getRetroInstance().create(LessonAPI::class.java)
        val call = retroInstance.getLessons()
        call.enqueue(object : Callback<List<LessonModel>> {
            override fun onResponse(
                call: Call<List<LessonModel>>,
                response: Response<List<LessonModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<LessonModel>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Lesson list: "+ t.message.toString())
                }
            }
        })
    }

    fun getLesson(_id: String){
        val retroInstance = RetroInstance.getRetroInstance().create(LessonAPI::class.java)
        val call = retroInstance.getLesson(_id)
        call.enqueue(
            object : Callback<LessonModel> {
                override fun onResponse(
                    call: Call<LessonModel>,
                    response: Response<LessonModel>
                ) {
                    if (response.isSuccessful) {
                        createLiveData.postValue(response.body())
                    }else {
                        createLiveData.postValue(null)
                    }

                }

                override fun onFailure(call: Call<LessonModel>, t: Throwable?) {
                    createLiveData.postValue(null)
                    if (t != null) {
                        Log.d("MyApp", "Lesson GetOne: "+ t.message.toString())
                    }
                }
            }
        )
    }

    fun createLesson(lesson: LessonModel){
        val retroInstance = RetroInstance.getRetroInstance().create(LessonAPI::class.java)
        val call = retroInstance.createLesson(lesson)
        call.enqueue(object : Callback<LessonModel> {
            override fun onResponse(
                call: Call<LessonModel>,
                response: Response<LessonModel>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<LessonModel>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Lesson create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateLesson(_id: String, lesson: LessonModel){
        val retroInstance = RetroInstance.getRetroInstance().create(LessonAPI::class.java)
        val call = retroInstance.updateLesson(_id, lesson)
        call.enqueue(object : Callback<LessonModel> {
            override fun onResponse(
                call: Call<LessonModel>,
                response: Response<LessonModel>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<LessonModel>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Lesson Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteLesson(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(LessonAPI::class.java)
        val call = retroInstance.deleteLesson(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Lesson Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}