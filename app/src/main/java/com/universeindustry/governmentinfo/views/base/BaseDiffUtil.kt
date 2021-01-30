package com.universeindustry.governmentinfo.views.base

import androidx.recyclerview.widget.DiffUtil
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingModel
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseModel

class BaseDiffUtil (private val oldList : MutableList<Any>,
                    private val currentList : List<Any>,
                    private val type : Any) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(type){
            is LicenseModel -> {
                (oldList as MutableList<LicenseModel>)[oldItemPosition] == (currentList as MutableList<LicenseModel>)[newItemPosition]
            }
            is BankDespositModelTree -> {
                (oldList as MutableList<BankDespositModelTree>)[oldItemPosition] == (currentList as MutableList<BankDespositModelTree>)[newItemPosition]
            }
            is FundingModel -> {
                (oldList as MutableList<FundingModel>)[oldItemPosition] == (currentList as MutableList<FundingModel>)[newItemPosition]
            }
            else -> {
                (oldList as MutableList<LicenseModel>)[oldItemPosition] == (currentList as MutableList<LicenseModel>)[newItemPosition]
            }
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(type){
            is LicenseModel -> {
                (oldList as MutableList<LicenseModel>)[oldItemPosition].title == (currentList as MutableList<LicenseModel>)[newItemPosition].title
            }
            is BankDespositModelTree -> {
                (oldList as MutableList<BankDespositModelTree>)[oldItemPosition].fin_prdt_nm == (currentList as MutableList<BankDespositModelTree>)[newItemPosition].fin_prdt_nm
            }
            is FundingModel -> {
                (oldList as MutableList<FundingModel>)[oldItemPosition].title == (currentList as MutableList<FundingModel>)[newItemPosition].title
            }
            else -> {
                (oldList as MutableList<LicenseModel>)[oldItemPosition].title == (currentList as MutableList<LicenseModel>)[newItemPosition].title
            }
        }
    }

    // 변경 전 리스트 크기
    override fun getOldListSize(): Int = oldList.size

    // 변경 후 리스트 크기
    override fun getNewListSize(): Int = currentList.size
}