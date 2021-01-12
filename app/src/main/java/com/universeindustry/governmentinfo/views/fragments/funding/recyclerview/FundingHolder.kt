package com.universeindustry.governmentinfo.views.fragments.funding.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderFundingBinding

class FundingHolder(
    val holderBinding : HolderFundingBinding
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : FundingModel){
        holderBinding.apply {
            setVariable(BR.fundingModel, model)
            executePendingBindings()
        }
    }

}