package com.dicoding.github_app.UI.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github_app.Api.ApiClient
import com.dicoding.github_app.data.Model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersView : ViewModel() {
    private val _followers = MutableLiveData<ArrayList<User>>()
    val followers: LiveData<ArrayList<User>> get() = _followers

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val api = ApiClient.api
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun setFollowersByUsername(username: String) {
        _isLoading.value = true
        api.getFollowers(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    val followersList = response.body()
                    if (followersList != null) {
                        _followers.postValue(followersList)
                        if (followersList.isEmpty()) {
                            // Tampilkan peringatan jika daftar pengikut kosong
                            _errorMessage.postValue("Data pengikut tidak ditemukan")
                        }
                    } else {
                        _errorMessage.postValue("Followers data is null")
                    }
                } else {
                    val errorCode = response.code()
                    val errorMessage = response.message()
                    _errorMessage.postValue("Error $errorCode: $errorMessage")
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.e("Failure", t.message ?: "Unknown error")
                _errorMessage.postValue("Failure: ${t.message ?: "Unknown error"}")
                _isLoading.value = false // Pastikan isLoading juga diatur ke false pada kegagalan
            }
        })
    }

    fun getFollowersByUsername(): LiveData<ArrayList<User>> {
       return _followers
    }

}
