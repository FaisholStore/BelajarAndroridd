    package com.dicoding.github_app.Api

    import com.dicoding.github_app.data.Model.DetailUserResponse
    import com.dicoding.github_app.data.Model.User
    import com.dicoding.github_app.data.Model.UserResponse
    import retrofit2.Call
    import retrofit2.http.GET
    import retrofit2.http.Headers
    import retrofit2.http.Path
    import retrofit2.http.Query

    interface Api {
        @GET("search/users")
        @Headers("Authorization: token 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")
        fun searchUsers(
            @Query("q") query: String
        ): Call<UserResponse>

        @GET("users/{username}")
        @Headers("Authorization: token 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")
        fun getUserDetail(

            @Path("username") username: String
        ): Call<DetailUserResponse>

        @GET("users/{username}/followers")
        @Headers("Authorization: token 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")
        fun getFollowers(
            @Path("username") username: String
        ): Call<ArrayList<User>>

        @GET("users/{username}/following")
        @Headers("Authorization: token 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")
        fun getFollowing(
            @Path("username") username: String
        ): Call<ArrayList<User>>

    }
