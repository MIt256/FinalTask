package com.example.finaltask.di.component

import android.app.Application
import com.example.finaltask.di.modules.*
import com.example.finaltask.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, VMModule::class, GlideModule::class, Binding::class])
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}