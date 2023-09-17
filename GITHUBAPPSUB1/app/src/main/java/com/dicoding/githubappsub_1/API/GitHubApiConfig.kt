package com.dicoding.githubappsub_1.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubApiConfig {
    private const val BASE_URL = "https://api.github.com/"

    // Fungsi untuk mengambil instance Retrofit yang sudah dikonfigurasi
    fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Fungsi untuk mengambil instance GitHubApiService
    fun getGitHubApiService(): GitHubApiService {
        return getRetrofit().create(GitHubApiService::class.java)
    }
}
