package com.debasish.texttospeechcoverter.dagger.module

import dagger.Module
import androidx.lifecycle.ViewModel
import com.debasish.texttospeechcoverter.dagger.ViewModelKey
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryListingViewModel
import dagger.multibindings.IntoMap
import dagger.Binds



@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DictionaryListingViewModel::class)
    abstract fun bindWeatherViewModel(dictionaryListingViewModel: DictionaryListingViewModel): ViewModel

}