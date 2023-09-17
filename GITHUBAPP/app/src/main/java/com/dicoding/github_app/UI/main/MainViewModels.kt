package com.dicoding.github_app.UI.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.github_app.data.Model.User
import com.dicoding.github_app.data.Model.UserResponse
import com.dicoding.github_app.Api.Api
import com.dicoding.github_app.Api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val api: Api = ApiClient.api // Pastikan Anda sudah memiliki objek ApiClient dengan konfigurasi yang sesuai

    fun searchUsers(query: String) {
        // Lakukan permintaan ke API untuk mencari pengguna
        api.searchUsers(query).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        val usersList = userResponse.items
                        _users.postValue(usersList) // Menggunakan postValue untuk mengirim data ke LiveData
                    }
                } else {
                    // Tanggapan tidak berhasil, Anda dapat mengecek kode respons untuk menentukan kesalahan yang mungkin terjadi
                    val errorCode = response.code()
                    // Handle kesalahan di sini
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Tanggapan gagal, tampilkan pesan kesalahan di sini
                // Handle kesalahan jaringan atau lainnya
            }
        })
    }
}
