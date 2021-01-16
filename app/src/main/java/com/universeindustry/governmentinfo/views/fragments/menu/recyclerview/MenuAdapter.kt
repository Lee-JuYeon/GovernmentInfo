package com.universeindustry.governmentinfo.views.fragments.menu.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.HolderMenuBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener


class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val menuList = arrayListOf(
        MenuModel(
            image = R.drawable.image_bank,
            title = "지원금"
        ),
        MenuModel(
            image = R.drawable.image_license,
            title = "국가 자격증"
        )
    )

    private var iClickListener : IClickListener? = null
    fun setClickListener(get : IClickListener?){ this.iClickListener = get }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBindingUtil = HolderMenuBinding.inflate(layoutInflater, parent, false)
        return  MenuHolder(dataBindingUtil, iClickListener)
    }
    override fun getItemCount(): Int = menuList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MenuHolder) {
            val dataList = menuList[position]
            holder.dataBinding(
                model = dataList
            )
        } else {
            val exception = Exception()
            throw Exception("MenuAdapter, onBindViewHolder // Exception : ${exception.message}")
        }
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is MenuHolder) {
            holder.dataBinding(
                model = null
            )
        } else {
            val exception = Exception()
            throw Exception("MenuAdapter, onViewRecycled // Exception : ${exception.message}")
        }
        super.onViewRecycled(holder)
    }
}