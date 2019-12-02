package com.debasish.texttospeechcoverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.debasish.texttospeechcoverter.data.DictionaryRepository
import com.debasish.texttospeechcoverter.data.models.DictionaryItem
import com.debasish.texttospeechcoverter.data.models.DictionaryListResponse
import com.debasish.texttospeechcoverter.domain.DictionaryUseCase
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.VMDictionary
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock

class DictionaryUseCaseUnitText {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val dictionaryRepository = mock<DictionaryRepository>()

    val dictionaryListUseCases by lazy { DictionaryUseCase(dictionaryRepository) }

    @Test
    fun testdictionaryListUseCases_getdictionaryList_Completed() {
        whenever(dictionaryRepository.fetchDictionaryList(anyString()))
            .thenReturn(Single.just(DictionaryListResponse(emptyList())))

        dictionaryListUseCases.fetchDictionaryList("")
            .test()
            .assertComplete()
    }

    @Test
    fun testdictionaryListUseCases_getdictionaryList_Error() {
        val response = Throwable("Error response")
        whenever(dictionaryRepository.fetchDictionaryList(anyString()))
            .thenReturn(Single.error(response))

        dictionaryListUseCases.fetchDictionaryList("")
            .test()
            .assertError(response)

    }

    @Test
    fun testdictionaryListUseCases_getdictionaryList_response() {
        val response = arrayListOf(dictionaryPOJOmodel())
        whenever(dictionaryRepository.fetchDictionaryList(anyString()))
            .thenReturn(Single.just(DictionaryListResponse(response)))

        val expectedList = arrayListOf(dictionaryViewModelFrom(dictionaryPOJOmodel()))

        dictionaryListUseCases.fetchDictionaryList("")
            .test()
            .assertValue(VMDictionary(expectedList))
    }
}