package com.debasish.texttospeechcoverter.data.models

import androidx.databinding.ObservableField


data class DictionaryListResponse(var dictionary:List<DictionaryItem>)
data class DictionaryItem(var word:String,var frequency:String)
