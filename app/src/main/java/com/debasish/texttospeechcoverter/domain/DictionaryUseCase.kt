package com.debasish.texttospeechcoverter.domain

import com.debasish.texttospeechcoverter.data.IDictionaryRepository
import com.debasish.texttospeechcoverter.domain.mapper.DictionaryMapper
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.VMDictionary
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class DictionaryUseCase @Inject constructor(private val dictionaryRepository: IDictionaryRepository){

    fun fetchDictionaryList(path:String):Single<VMDictionary>{
    return dictionaryRepository.fetchDictionaryList(path)
    .map { DictionaryMapper.toVM(it) }
     .observeOn(AndroidSchedulers.mainThread())
    }
}
