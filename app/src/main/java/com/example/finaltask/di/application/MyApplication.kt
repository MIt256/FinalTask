package com.example.finaltask.di.application

import android.app.Application
import com.example.finaltask.di.component.ApplicationComponent
import com.example.finaltask.di.component.DaggerApplicationComponent


class MyApplication : Application() {
    private val component by lazy { DaggerApplicationComponent.builder()
        .application(this)
        .build()}

    fun getApplicationComponent(): ApplicationComponent = component
}