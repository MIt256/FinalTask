package com.example.finaltask.di.modules

import com.example.finaltask.constants.Constants.Companion.URL_BASE
import com.example.finaltask.models.QuestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideService(retrofitObj: Retrofit): QuestApi =
        retrofitObj.create(QuestApi::class.java)
}