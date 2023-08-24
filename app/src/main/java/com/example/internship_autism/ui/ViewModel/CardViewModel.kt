package com.example.internship_autism.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internship_autism.interfaces.CardAPI
import com.example.internship_autism.models.Card
import com.example.internship_autism.utils.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CardViewModel:  ViewModel() {

    lateinit var recyclerListData: MutableLiveData<List<Card>>
    lateinit var getLiveData: MutableLiveData<Card>
    lateinit var createLiveData: MutableLiveData<Card?>
    lateinit var updateLiveData: MutableLiveData<Card?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        getLiveData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getCardListObservable(): MutableLiveData<List<Card>> {
        return recyclerListData
    }

    fun getCardObservable(): MutableLiveData<Card> {
        return getLiveData
    }

    fun getCreateNewCardObservable(): MutableLiveData<Card?> {
        return createLiveData
    }

    fun getUpdateCardObservable(): MutableLiveData<Card?> {
        return updateLiveData
    }

    fun deleteCardObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getCardList() {
        val retroInstance = RetroInstance.getRetroInstance().create(CardAPI::class.java)
        val call = retroInstance.getCards()
        call.enqueue(object : Callback<List<Card>> {
            override fun onResponse(
                call: Call<List<Card>>,
                response: Response<List<Card>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Card list: "+ t.message.toString())
                }
            }
        })
    }

    fun getCard(_id: String){
        val retroInstance = RetroInstance.getRetroInstance().create(CardAPI::class.java)
        val call = retroInstance.getCard(_id)
        call.enqueue(
            object : Callback<Card>{
                override fun onResponse(
                    call: Call<Card>,
                    response: Response<Card>
                ) {
                    if (response.isSuccessful) {
                        createLiveData.postValue(response.body())
                    }else {
                        createLiveData.postValue(null)
                    }

                }

                override fun onFailure(call: Call<Card>, t: Throwable?) {
                    createLiveData.postValue(null)
                    if (t != null) {
                        Log.d("MyApp", "Card GetOne: "+ t.message.toString())
                    }
                }
            }
        )
    }

    fun createCard(card: Card){
        val retroInstance = RetroInstance.getRetroInstance().create(CardAPI::class.java)
        val call = retroInstance.createCard(card)
        call.enqueue(object : Callback<Card>{
            override fun onResponse(
                call: Call<Card>,
                response: Response<Card>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Card>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Card Create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateCard(_id: String, card: Card){
        val retroInstance = RetroInstance.getRetroInstance().create(CardAPI::class.java)
        val call = retroInstance.updateCard(_id, card)
        call.enqueue(object : Callback<Card>{
            override fun onResponse(
                call: Call<Card>,
                response: Response<Card>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Card>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Card Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteCard(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(CardAPI::class.java)
        val call = retroInstance.deleteCard(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Card Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}