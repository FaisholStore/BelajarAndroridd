package com.dicoding.github_app.data.Model

data class UserResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items : ArrayList <User>

)

