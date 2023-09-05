package com.example.internship_autism.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internship_autism.interfaces.SubjectAPI
import com.example.internship_autism.models.SubjectModel
import com.example.internship_autism.utils.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectViewModel:  ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<SubjectModel>>
    lateinit var getLiveData: MutableLiveData<SubjectModel>
    lateinit var createLiveData: MutableLiveData<SubjectModel?>
    lateinit var updateLiveData: MutableLiveData<SubjectModel?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        getLiveData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getSubjectListObservable(): MutableLiveData<List<SubjectModel>> {
        return recyclerListData
    }

    fun getSubjectObservable(): MutableLiveData<SubjectModel> {
        return getLiveData
    }

    fun getCreateNewSubjectObservable(): MutableLiveData<SubjectModel?> {
        return createLiveData
    }

    fun getUpdateSubjectObservable(): MutableLiveData<SubjectModel?> {
        return updateLiveData
    }

    fun deleteSubjectObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getSubjectList() {
        val retroInstance = RetroInstance.getRetroInstance().create(SubjectAPI::class.java)
        val call = retroInstance.getSubjects()
        call.enqueue(object : Callback<List<SubjectModel>> {
            override fun onResponse(
                call: Call<List<SubjectModel>>,
                response: Response<List<SubjectModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                    Log.d("MyApp", "Subject list: "+ response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject list: "+ t.message.toString())
                }
            }
        })
    }

    fun getSubject(_id: String){
        val retroInstance = RetroInstance.getRetroInstance().create(SubjectAPI::class.java)
        val call = retroInstance.getSubject(_id)
        call.enqueue(
            object : Callback<SubjectModel> {
                override fun onResponse(
                    call: Call<SubjectModel>,
                    response: Response<SubjectModel>
                ) {
                    if (response.isSuccessful) {
                        getLiveData.postValue(response.body())
                    }else {
                        getLiveData.postValue(null)
                    }

                }

                override fun onFailure(call: Call<SubjectModel>, t: Throwable?) {
                    getLiveData.postValue(null)
                    if (t != null) {
                        Log.d("MyApp", "Subject GetOne: "+ t.message.toString())
                    }
                }
            }
        )
    }

    fun createSubject(subject: SubjectModel){
        val retroInstance = RetroInstance.getRetroInstance().create(SubjectAPI::class.java)
        val call = retroInstance.createSubject(subject)
        call.enqueue(object : Callback<SubjectModel> {
            override fun onResponse(
                call: Call<SubjectModel>,
                response: Response<SubjectModel>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<SubjectModel>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateSubject(_id: String, subject: SubjectModel){
        val retroInstance = RetroInstance.getRetroInstance().create(SubjectAPI::class.java)
        val call = retroInstance.updateSubject(_id, subject)
        call.enqueue(object : Callback<SubjectModel> {
            override fun onResponse(
                call: Call<SubjectModel>,
                response: Response<SubjectModel>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<SubjectModel>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteSubject(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(SubjectAPI::class.java)
        val call = retroInstance.deleteSubject(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Subject Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}