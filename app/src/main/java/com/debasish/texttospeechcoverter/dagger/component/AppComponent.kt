package com.debasish.texttospeechcoverter.dagger.component

import android.app.Application
import com.debasish.texttospeechcoverter.dagger.module.AppModule
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryListingActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
 interface AppComponent {
     fun inject(weatherActivity: DictionaryListingActivity)
     @Component.Builder
     interface Builder{
          @BindsInstance
          fun application(application: Application): Builder
          fun build(): AppComponent
     }
}