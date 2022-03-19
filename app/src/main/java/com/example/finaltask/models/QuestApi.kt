package com.example.finaltask.models

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestApi {
    @GET("/tt/meta/")
    suspend fun getDataApi(): Response<Item>

    @POST("/tt/data/")
    suspend fun setDataApi(@Body post:Post): Response<Answer>
}