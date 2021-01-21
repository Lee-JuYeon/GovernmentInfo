package com.universeindustry.governmentinfo.views.fragments.funding.recyclerview

import android.util.Log.e
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderFundingBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class FundingHolder(
        private val holderBinding : HolderFundingBinding,
        private val iClickListener: IClickListener
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : Any?){
        holderBinding.apply {
            setVariable(BR.fundingModel, model as FundingModel)
            executePendingBindings()
        }
    }

    init {
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        iClickListener.onClick(adapterPosition,"${holderBinding.title.text}")
                    }catch (e:Exception){
                        e("mException", "에러발생 -> FundingHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}