package com.universeindustry.governmentinfo.views.fragments.funding.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class FundingAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val mList = arrayListOf<FundingModel>(
        FundingModel(
            image = R.drawable.background_all_radius,
            title = "타이틀11",
            date = "date 11",
            money = "1won",
            option = "option 1"
        ),
        FundingModel(
            image = R.drawable.background_all_radius,
            title = "타이틀22",
            date = "date 22",
            money = "1won",
            option = "option 1"
        ),
        FundingModel(
            image = R.drawable.background_all_radius,
            title = "타이틀33",
            date = "date 33",
            money = "1won",
            option = "option 1"
        ),
        FundingModel(
            image = R.drawable.background_all_radius,
            title = "타이틀44",
            date = "date 44",
            money = "1won",
            option = "option 1"
        )
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FundingHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_funding, parent,false)
        )
    override fun getItemCount(): Int = mList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FundingHolder){
            val dataList = mList[position]
            holder.dataBinding(dataList)
        }else{
            val exception = Exception()
            throw Exception("FundingAdapter, onBindViewHolder // Exception : ${exception.message}")
        }
    }

    fun setInFun(txtTest : String?){
        Log.e("mException", "클린된 것 : ${txtTest}")
    }
}