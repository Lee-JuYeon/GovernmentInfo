package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.ChartDeposit

import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderChartDepositBinding
import com.universeindustry.governmentinfo.online.retrofit.model.OptionListItem

class HolderChartDeposit (
        private val binding : HolderChartDepositBinding
) : RecyclerView.ViewHolder(binding.root){
    fun dataBinding(model : OptionListItem?){
        binding.apply {
            setVariable(BR.listItem, model)
            executePendingBindings()
        }
    }
}