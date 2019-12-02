package com.debasish.texttospeechcoverter.presentation.dictionary_listing

data class VMDictionary(var dictionary:List<DictionaryItem>)
data class DictionaryItem(var word:String,var frequency:String,var isHighlighted:Boolean)