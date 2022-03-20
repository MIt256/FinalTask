package com.example.finaltask.repository

import com.example.finaltask.api.QuestApi
import com.example.finaltask.models.Answer
import com.example.finaltask.models.Item
import com.example.finaltask.models.Form
import javax.inject.Inject

class Repository @Inject constructor(private val api: QuestApi) {
    suspend fun getData(): Item? {
        return api.getDataApi().body()
    }

    suspend fun setData(post: Form): Answer? {
        return api.setDataApi(post).body()
    }
}