package com.debasish.texttospeechcoverter.dagger.module

import com.debasish.texttospeechcoverter.data.DictionaryRepository
import com.debasish.texttospeechcoverter.data.IDictionaryRepository
import com.debasish.texttospeechcoverter.data.api.RetrofitAPI
import com.debasish.texttospeechcoverter.utils.UnsafeOkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSession


@Module(includes = [ViewModelModule::class,ViewModelFactoryModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideCompositeDecomposible(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    public fun provideWeatherRepo( retrofitAPi: RetrofitAPI):IDictionaryRepository{
        return DictionaryRepository(retrofitAPi)
    }

    @Singleton
    @Provides
    public  fun provideRetrofit() :Retrofit {
       val baseUrl = "https://a.galactio.com/"
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(UnsafeOkHttpClient.unsafeOkHttpClient
                        .addInterceptor( HttpLoggingInterceptor()
                                 .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addNetworkInterceptor(interceptor)
                        .connectTimeout(6000, TimeUnit.SECONDS)
                        .readTimeout(600, TimeUnit.SECONDS)
                        .writeTimeout(600, TimeUnit.SECONDS)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public fun provideRetrofitAPI(retrofit:Retrofit):RetrofitAPI{
         return retrofit.create(RetrofitAPI::class.java)
    }

}