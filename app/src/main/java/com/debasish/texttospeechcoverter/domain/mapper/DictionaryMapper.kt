package com.debasish.texttospeechcoverter.domain.mapper

import com.debasish.texttospeechcoverter.data.models.*
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.*
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryItem


object  DictionaryMapper {
    fun toVM(dictionaryMapper: DictionaryListResponse):VMDictionary{

        val list= mutableListOf<DictionaryItem>()

        dictionaryMapper.dictionary.forEach { item ->
            list.add(DictionaryItem(item.word,item.frequency,false))
        }
          return  VMDictionary(list)
        }
    }