package com.debasish.texttospeechcoverter.presentation.dictionary_listing

import androidx.lifecycle.ViewModel
import com.debasish.texttospeechcoverter.domain.DictionaryUseCase
import io.reactivex.Single
import javax.inject.Inject


public  class DictionaryListingViewModel @Inject constructor(internal val dictionaryUserCase: DictionaryUseCase) :ViewModel(){


    private var adapter: DictionaryAdapter?=null

    init {
        adapter = DictionaryAdapter()
    }
    fun getDictionaryListing(pathName: String):Single<VMDictionary>{
        return dictionaryUserCase.fetchDictionaryList(pathName)
    }
    fun getAdapter(): DictionaryAdapter? {
        return adapter
    }

    fun setDictionaryListAdapter(dictionaryList: MutableList<DictionaryItem>) {
        adapter?.dictionaryList=dictionaryList
        adapter?.notifyDataSetChanged()
    }
}