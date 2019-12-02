package com.debasish.texttospeechcoverter.dagger.module

import androidx.lifecycle.ViewModelProvider
import com.debasish.texttospeechcoverter.utils.CustumViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: CustumViewModelFactory): ViewModelProvider.Factory
}