package com.example.finaltask.di.component

import com.example.finaltask.di.modules.NetworkModule
import com.example.finaltask.view.MainActivity
import com.example.finaltask.di.modules.VMModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, VMModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}