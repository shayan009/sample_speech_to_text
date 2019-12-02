package com.debasish.texttospeechcoverter

import android.app.Application
import com.debasish.texttospeechcoverter.dagger.component.AppComponent
import com.debasish.texttospeechcoverter.dagger.component.DaggerAppComponent

class MainApp :Application() {


    companion object{
        public lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}