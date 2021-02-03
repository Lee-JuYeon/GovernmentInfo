package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.online.retrofit.model.OptionListItem
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingModel
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseModel


class DiffUtilBank(private val oldList: MutableList<BankDespositModelTree>,
                   private val currentList: ArrayList<BankDespositModelTree>) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == currentList[newItemPosition]

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].fin_prdt_nm == currentList[newItemPosition].fin_prdt_nm

    }

    // 변경 전 리스트 크기
    override fun getOldListSize(): Int = oldList.size

    // 변경 후 리스트 크기
    override fun getNewListSize(): Int = currentList.size
}