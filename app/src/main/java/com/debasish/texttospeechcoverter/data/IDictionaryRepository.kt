package com.debasish.texttospeechcoverter.data

import com.debasish.texttospeechcoverter.data.models.DictionaryListResponse
import io.reactivex.Single

interface IDictionaryRepository {
    fun fetchDictionaryList(dicPath:String):Single<DictionaryListResponse>
}