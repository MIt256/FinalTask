package com.example.finaltask.models

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface QuestApi {
    @GET("/tt/meta/")
    suspend fun getDataApi(): Response<Item>
}