package com.example.finaltask.data.api

import com.example.finaltask.constants.Constants.Companion.URL_ENDPOINT_GET
import com.example.finaltask.constants.Constants.Companion.URL_ENDPOINT_POST
import com.example.finaltask.data.models.Answer
import com.example.finaltask.data.models.Item
import com.example.finaltask.data.models.Form
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestApi {
    @GET(URL_ENDPOINT_GET)
    suspend fun getDataApi(): Response<Item>

    @POST(URL_ENDPOINT_POST)
    suspend fun setDataApi(@Body post: Form): Response<Answer>
}