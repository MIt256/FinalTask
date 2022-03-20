package com.example.finaltask.models

import com.example.finaltask.constants.Constants.Companion.URL_ENDPOINT_GET
import com.example.finaltask.constants.Constants.Companion.URL_ENDPOINT_POST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestApi {
    @GET(URL_ENDPOINT_GET)
    suspend fun getDataApi(): Response<Item>

    @POST(URL_ENDPOINT_POST)
    suspend fun setDataApi(@Body post:Post): Response<Answer>
}