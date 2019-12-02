package com.debasish.texttospeechcoverter.presentation.dictionary_listing

import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryAdapter
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryItem
import com.debasish.texttospeechcoverter.presentation.dictionary_listing.DictionaryListingViewModel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debasish.texttospeechcoverter.MainApp
import com.debasish.texttospeechcoverter.R
import com.debasish.texttospeechcoverter.databinding.ActivityDictionaryListBinding
import com.debasish.texttospeechcoverter.utils.CustomAlertDialog
import com.debasish.texttospeechcoverter.utils.CustumViewModelFactory
import com.debasish.texttospeechcoverter.utils.getItemDecorator
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_dictionary_list.*
import javax.inject.Inject

class DictionaryListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDictionaryListBinding
    private val RECOGNITION_REQUEST_CODE: Int=1
    private lateinit var dictionaryAdapter: DictionaryAdapter
    private var weatherViewModel: DictionaryListingViewModel? = null

    @Inject
    public lateinit var viewModelFactory : CustumViewModelFactory

    var compositeDisposable=CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_list)
        MainApp.appComponent.inject(this)
        weatherViewModel = ViewModelProviders.of(this,viewModelFactory).get(DictionaryListingViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dictionary_list);
        binding.model=weatherViewModel

        getItemDecorator(R.dimen._10dp)?.let { rvDictionary.addItemDecoration(it) }
        weatherViewModel?.getDictionaryListing("interview/dictionary-v2.json")
            ?.subscribe({ t1 ->
                if(t1!=null){
                    Log.d("dictionary",t1.dictionary.toString())
                    binding.model?.setDictionaryListAdapter(t1.dictionary.sortedBy {it.frequency.toInt()}.reversed().toMutableList())
                }

            },{
                it.printStackTrace()
            })?.let { compositeDisposable.add(it) }

        ivRecordIcon.setOnClickListener {
                    startVoiceRecognitionActivity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var isVoiceSame=false
        var currentIndex=0
        if (requestCode == RECOGNITION_REQUEST_CODE&& resultCode == Activity.RESULT_OK) {
            val matches = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            Log.d("TAG", matches.toString())

            for(i in matches?.indices!!){
                val speech=matches[i]
                val list=binding.model?.getAdapter()?.dictionaryList
                for(i in list?.indices!!){
                    val dictionaryItem=list[i]
                    Log.d("TAGspeech", dictionaryItem.word)
                    Log.d("TAGspeech",speech)
                    if(speech.equals(dictionaryItem.word,true)){
                        isVoiceSame =true
                        currentIndex=i
                        break
                    }
                }
                if(isVoiceSame)
                  break
            }
            if(isVoiceSame){

                binding.model?.getAdapter()?.dictionaryList?.forEach { it.isHighlighted=false }
                binding.model?.getAdapter()?.dictionaryList?.get(currentIndex)?.frequency= (dictionaryAdapter.dictionaryList?.get(currentIndex)?.frequency?.toInt()?.plus(
                    1
                )).toString()
                binding.model?.getAdapter()?.dictionaryList?.get(currentIndex)?.isHighlighted=true
                binding.model?.getAdapter()?.notifyDataSetChanged()
            }else{
                val alertDialog= CustomAlertDialog()
                alertDialog.show(supportFragmentManager,"CustomAlertDialog")
            }
        }
    }


    fun startVoiceRecognitionActivity() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Speech recognition demo"
        )
        startActivityForResult(intent,RECOGNITION_REQUEST_CODE)
    }

    private fun initAdapter() {

    }

    fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()
}
