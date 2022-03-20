package com.example.finaltask.domain.repository

import com.example.finaltask.data.api.QuestApi
import com.example.finaltask.data.models.Answer
import com.example.finaltask.data.models.Item
import com.example.finaltask.data.models.Form
import javax.inject.Inject

class Repository @Inject constructor(private val api: QuestApi) {
    suspend fun getData(): Item? {
        return api.getDataApi().body()
    }

    suspend fun setData(post: Form): Answer? {
        return api.setDataApi(post).body()
    }
}