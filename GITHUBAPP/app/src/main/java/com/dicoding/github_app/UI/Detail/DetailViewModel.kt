package com.dicoding.github_app.UI.Detail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.github_app.Api.ApiClient
import com.dicoding.github_app.data.Model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel : ViewModel() {

    val user = MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username: String) {
        ApiClient.api
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            user.postValue(responseBody)
                        } else if (response.code() == 404) {
                            Log.e(
                                "Response Error",
                                "Resource not found: ${response.raw().request().url()}"
                            )
                        } else {
                            Log.e("Response Error", "Response body is null")
                        }
                    } else {
                        val errorCode = response.code()
                        val errorMessage = response.message()
                        Log.e("Response Error", "Error $errorCode: $errorMessage")
                    }

                }


                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.e("Failure", t.message ?: "Unknown error")
                }


            })
    }

    fun  getUserDetail(): LiveData<DetailUserResponse>{
        return  user
    }

}
