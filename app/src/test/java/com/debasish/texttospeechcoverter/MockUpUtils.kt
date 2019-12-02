package com.debasish.texttospeechcoverter

import com.debasish.texttospeechcoverter.data.models.DictionaryListResponse
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryItem
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock() = Mockito.mock(T::class.java)
inline fun <T> whenever(methodCall: T) : OngoingStubbing<T> =
    Mockito.`when`(methodCall)

fun dictionaryPOJOmodel() =
   com.debasish.texttospeechcoverter.data.models.DictionaryItem("helllo", "5")
fun dictionaryViewModelFrom(dictionaryItem: com.debasish.texttospeechcoverter.data.models.DictionaryItem) =
    DictionaryItem(dictionaryItem.word, dictionaryItem.frequency,false)


