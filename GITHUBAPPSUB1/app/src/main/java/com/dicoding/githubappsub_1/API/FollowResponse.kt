package com.dicoding.githubappsub_1.API

import com.google.gson.annotations.SerializedName

data class FollowResponse(

	@field:SerializedName("FollowResponse")
	val followResponse: List<FollowResponseItem>
)

data class FollowResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String
)
