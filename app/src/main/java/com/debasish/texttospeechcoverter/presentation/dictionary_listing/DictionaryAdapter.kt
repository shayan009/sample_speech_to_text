package com.debasish.texttospeechcoverter.presentation.dictionary_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.debasish.texttospeechcoverter.BR
import com.debasish.texttospeechcoverter.R
import com.debasish.texttospeechcoverter.databinding.HolderItemBinding


class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

     var dictionaryList: MutableList<DictionaryItem> ?=null
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflate the layout file
        val happyHourProductView: HolderItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.holder_item, parent, false)
        return ViewHolder(happyHourProductView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataModel: DictionaryItem = dictionaryList?.get(position)!!
        holder.bind(dataModel)
    }

    override fun getItemCount(): Int {
        return if(dictionaryList!=null&& dictionaryList!!.size>0)
            dictionaryList?.size!!
        else
            0
    }

    class ViewHolder(itemRowBinding: HolderItemBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        var itemRowBinding: HolderItemBinding = itemRowBinding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.item, obj)
            itemRowBinding.executePendingBindings()
        }

    }

/*inner class ViewHolder(view: HolderItemBinding) : RecyclerView.ViewHolder(view) {



        fun bind(obj: Objects, position: Int) {

            itemRowBinding.setVariable(BR.item, obj);
            itemRowBinding.executePendingBindings();

            if(rule.isHighlighted){
                itemView.tvWord.setTextColor(itemView.resources.getColor(R.color.orange))//chor_pending_grey
            }else{
                itemView.tvWord.setTextColor(itemView.resources.getColor(R.color.chor_pending_grey))
            }
        }

    }*/

}