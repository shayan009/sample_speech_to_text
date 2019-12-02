package com.debasish.texttospeechcoverter.data.api


import com.debasish.texttospeechcoverter.data.models.DictionaryListResponse
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitAPI {


    @GET("{path}")
    fun fetchDictionaryList(@Path("path") pathUrl:String): Single<DictionaryListResponse>
}