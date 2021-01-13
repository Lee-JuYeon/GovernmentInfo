package com.universeindustry.governmentinfo.views.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.views.fragments.menu.recyclerview.MenuModel
import java.util.*


abstract class BaseViewHolder<in Model : Objects>(
        private val BR : Int,
        private val holderBinding : ViewDataBinding
) : RecyclerView.ViewHolder(holderBinding.root) {


    fun onBindViewHolder(model : Objects){
        try {
//            onViewCreated(model)
            holderBinding.apply {
                setVariable(BR, model)
                executePendingBindings()
            }
        }catch (e:Exception){
            Log.e("mException", "BaseViewHolder, onBindViewHolder // Exception : ${e.message}")
        }
    }

    abstract fun onViewCreated(model : Model)
}
/*
abstract class BaseViewHolder<in ITEM : Any>(
        val context: Context,
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false)) {

    fun onBindViewHolder(item: Any?) {
        try {
            onViewCreated(item as? ITEM?)
        } catch (e: Exception) {
            itemView.visibility = View.GONE
        }
    }

    abstract fun onViewCreated(item: ITEM?)
}
https://thdev.tech/android/2018/01/31/Recycler-Adapter-Distinguish/
 */