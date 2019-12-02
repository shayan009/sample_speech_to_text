package com.debasish.texttospeechcoverter.data

import com.debasish.texttospeechcoverter.data.api.RetrofitAPI
import com.debasish.texttospeechcoverter.data.models.DictionaryListResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DictionaryRepository(var retrofitAPi: RetrofitAPI)  :IDictionaryRepository{



    override fun fetchDictionaryList(dicPath: String): Single<DictionaryListResponse> {
       return retrofitAPi.fetchDictionaryList(dicPath).subscribeOn(Schedulers.io())
    }
///interview/dictionary-v2.json

}