package com.dicoding.githubappsub_1.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/users")
    @Headers("Authorization:token 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")
    fun getUsers(
        @Query("q") username:String,
    ): Call<SeacrhuserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username:String,
    ):Call<DetailuserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("username") username:String
    ):Call<List<FollowResponseItem>>
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("username") username:String
    ):Call<List<FollowResponseItem>>
}
