package com.example.internship_autism.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internship_autism.interfaces.ProgressionAPI
import com.example.internship_autism.models.ProgressionModel
import com.example.internship_autism.utils.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgressionViewModel:  ViewModel(){

    lateinit var recyclerListData: MutableLiveData<List<ProgressionModel>>
    lateinit var getLiveData: MutableLiveData<ProgressionModel>
    lateinit var createLiveData: MutableLiveData<ProgressionModel?>
    lateinit var updateLiveData: MutableLiveData<ProgressionModel?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        getLiveData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getProgressionListObservable(): MutableLiveData<List<ProgressionModel>> {
        return recyclerListData
    }

    fun getProgressionObservable(): MutableLiveData<ProgressionModel> {
        return getLiveData
    }

    fun getCreateNewProgressionObservable(): MutableLiveData<ProgressionModel?> {
        return createLiveData
    }

    fun getUpdateProgressionObservable(): MutableLiveData<ProgressionModel?> {
        return updateLiveData
    }

    fun deleteProgressionObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getProgressionList() {
        val retroInstance = RetroInstance.getRetroInstance().create(ProgressionAPI::class.java)
        val call = retroInstance.getProgressions()
        call.enqueue(object : Callback<List<ProgressionModel>>{
            override fun onResponse(
                call: Call<List<ProgressionModel>>,
                response: Response<List<ProgressionModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<ProgressionModel>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Progression List: "+ t.message.toString())
                }
            }
        })
    }

    fun getProgression(_id: String){
        val retroInstance = RetroInstance.getRetroInstance().create(ProgressionAPI::class.java)
        val call = retroInstance.getProgression(_id)
        call.enqueue(
            object : Callback<ProgressionModel>{
                override fun onResponse(
                    call: Call<ProgressionModel>,
                    response: Response<ProgressionModel>
                ) {
                    if (response.isSuccessful) {
                        createLiveData.postValue(response.body())
                    }else {
                        createLiveData.postValue(null)
                    }

                }

                override fun onFailure(call: Call<ProgressionModel>, t: Throwable?) {
                    createLiveData.postValue(null)
                    if (t != null) {
                        Log.d("MyApp", "Progression GetOne: "+ t.message.toString())
                    }
                }
            }
        )
    }

    fun createProgression(progression: ProgressionModel){
        val retroInstance = RetroInstance.getRetroInstance().create(ProgressionAPI::class.java)
        val call = retroInstance.createProgression(progression)
        call.enqueue(object : Callback<ProgressionModel>{
            override fun onResponse(
                call: Call<ProgressionModel>,
                response: Response<ProgressionModel>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<ProgressionModel>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Progression Create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateProgression(_id: String, progression: ProgressionModel){
        val retroInstance = RetroInstance.getRetroInstance().create(ProgressionAPI::class.java)
        val call = retroInstance.updateProgression(_id, progression)
        call.enqueue(object : Callback<ProgressionModel>{
            override fun onResponse(
                call: Call<ProgressionModel>,
                response: Response<ProgressionModel>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<ProgressionModel>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Progression Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteProgression(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ProgressionAPI::class.java)
        val call = retroInstance.deleteProgression(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Progression Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}