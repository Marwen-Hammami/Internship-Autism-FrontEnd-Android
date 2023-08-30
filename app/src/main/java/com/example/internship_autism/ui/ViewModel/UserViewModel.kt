package com.example.internship_autism.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internship_autism.interfaces.UserAPI
import com.example.internship_autism.models.User
import com.example.internship_autism.utils.RetroInstance
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel:  ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<User>>
    lateinit var getLiveData: MutableLiveData<User>
    lateinit var createLiveData: MutableLiveData<User?>
    lateinit var updateLiveData: MutableLiveData<User?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>
    lateinit var login: MutableLiveData<User?>
    lateinit var changePassword: MutableLiveData<User?>

    init {
        recyclerListData = MutableLiveData()
        getLiveData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
        login = MutableLiveData()
        changePassword = MutableLiveData()
    }

    fun getUserListObservable(): MutableLiveData<List<User>> {
        return recyclerListData
    }

    fun getUserObservable(): MutableLiveData<User> {
        return getLiveData
    }

    fun getCreateNewUserObservable(): MutableLiveData<User?> {
        return createLiveData
    }

    fun getUpdateUserObservable(): MutableLiveData<User?> {
        return updateLiveData
    }

    fun deleteUserObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun logInObservable(): MutableLiveData<User?> {
        return login
    }

    fun changePasswordObservable(): MutableLiveData<User?> {
        return changePassword
    }

    fun getUserList() {
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "User list: "+ t.message.toString())
                }
            }
        })
    }

    fun getUser(_id: String){
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.getUser(_id)
        call.enqueue(
            object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        getLiveData.postValue(response.body())
                    }else {
                        getLiveData.postValue(null)
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable?) {
                    getLiveData.postValue(null)
                    if (t != null) {
                        Log.d("MyApp", "User GetOne: "+ t.message.toString())
                    }
                }
            }
        )
    }

    fun createUser(subject: User){
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.createUser(subject)
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "User create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateUser(_id: String, subject: User){
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.updateUser(_id, subject)
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "User Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteUser(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.deleteUser(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "User Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }

    fun logIn(subject: User){
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.logIn(subject)
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    login.postValue(response.body())
                }else {
                    login.postValue(null)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable?) {
                login.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "User login: "+ t.message.toString())
                }
            }
        })
    }

    fun changePassword(subject: User){
        val retroInstance = RetroInstance.getRetroInstance().create(UserAPI::class.java)
        val call = retroInstance.updatePassword( subject)
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    changePassword.postValue(response.body())
                }else {
                    changePassword.postValue(null)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable?) {
                changePassword.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "User Update password: "+ t.message.toString())
                }
            }
        })
    }
}