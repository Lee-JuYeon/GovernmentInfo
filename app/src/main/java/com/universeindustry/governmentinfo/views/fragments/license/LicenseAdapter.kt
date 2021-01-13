package com.universeindustry.governmentinfo.views.fragments.license

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.HolderLicenseBinding
import com.universeindustry.governmentinfo.databinding.HolderMenuBinding
import com.universeindustry.governmentinfo.views.fragments.menu.recyclerview.MenuHolder
import com.universeindustry.governmentinfo.views.fragments.menu.recyclerview.MenuModel
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener


class LicenseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val menuList = arrayListOf(
        LicenseModel(
            title = "타이틀",
            examFee = "만원",
            writeenTestDate = hashMapOf(),
            practicalTestDate = hashMapOf()
        )
    )

    private var iClickListener : IClickListener? = null
    fun setClickListener(get : IClickListener?){ this.iClickListener = get }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBindingUtil = HolderLicenseBinding.inflate(layoutInflater, parent, false)
        return LicenseHolder(dataBindingUtil, iClickListener)
    }
    override fun getItemCount(): Int = menuList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LicenseHolder) {
            val dataList = menuList[position]
            holder.dataBinding(
                model = dataList
            )
        } else {
            val exception = Exception()
            throw Exception("LicenseAdapter, onBindViewHolder // Exception : ${exception.message}")
        }
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is LicenseHolder) {
            holder.dataBinding(
                model = null
            )
        } else {
            val exception = Exception()
            throw Exception("LicenseAdapter, onViewRecycled // Exception : ${exception.message}")
        }
        super.onViewRecycled(holder)
    }
}