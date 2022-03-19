package com.example.finaltask.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finaltask.vm.MainViewModel
import com.example.finaltask.vm.VMFactory
import com.example.finaltask.di.VMKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @VMKey(MainViewModel::class)
    abstract fun bindVM(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindVMFactory(factory: VMFactory): ViewModelProvider.Factory
}