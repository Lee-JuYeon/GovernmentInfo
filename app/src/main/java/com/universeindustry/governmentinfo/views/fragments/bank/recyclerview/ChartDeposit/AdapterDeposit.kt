package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.ChartDeposit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.databinding.HolderChartDepositBinding
import com.universeindustry.governmentinfo.online.retrofit.model.OptionListItem
import com.universeindustry.governmentinfo.views.base.BaseDiffUtil
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.HolderTypeDeposit

class AdapterDeposit : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var customList : ArrayList<Any>? = arrayListOf()
    fun setList(newList : ArrayList<OptionListItem?>){
        // 리스트 업데이트
        val diffResult = DiffUtil.calculateDiff(
                BaseDiffUtil(
                        oldList = customList ?: arrayListOf(),
                        currentList = newList as ArrayList<Any>,
                        type = OptionListItem(null,null,null,null,null)
                ), false)
        diffResult.dispatchUpdatesTo(this@AdapterDeposit)
        customList?.clear()
        customList?.add(OptionListItem(
                intr_rate_type = "저축 금리 유형",
                intr_rate_type_nm = "유형",
                save_trm = "기간(월)",
                intr_rate = "금리",
                intr_rate2 = "우대금리"
        ))
        customList?.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val holderChartDepositBinding = HolderChartDepositBinding.inflate(layoutInflater, parent, false)
        return HolderChartDeposit(holderChartDepositBinding)
    }

    override fun getItemCount(): Int {
        return customList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HolderChartDeposit -> {
                val dataList = customList!![position]
                holder.dataBinding(
                        model = dataList as OptionListItem
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("AdapterDeposit, onBindViewHolder // Exception : ${exception.message}")
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when(holder){
            is HolderTypeDeposit -> {
                holder.dataBinding(
                        model = null
                )
            }
            is HolderChartDeposit -> {
                holder.dataBinding(
                        model = null
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("AdapterDeposit, onViewRecycled // Exception : ${exception.message}")
            }
        }
        super.onViewRecycled(holder)
    }
}