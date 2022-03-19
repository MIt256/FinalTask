package com.example.finaltask.models

import javax.inject.Inject

class Repository@Inject constructor(private val api: QuestApi) {
    suspend fun getData(): Item? {
        return api.getDataApi().body()
    }
}