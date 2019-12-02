package com.debasish.texttospeechcoverter.data.models


data class DictionaryListResponse(var dictionary:List<DictionaryItem>)
data class DictionaryItem(var word:String,var frequency:String)
