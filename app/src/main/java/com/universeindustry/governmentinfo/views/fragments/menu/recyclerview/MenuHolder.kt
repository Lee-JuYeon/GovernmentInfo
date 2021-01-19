package com.universeindustry.governmentinfo.views.fragments.menu.recyclerview

import android.util.Log.e
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderMenuBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener
import java.io.File

class MenuHolder(
    private val holderBinding: HolderMenuBinding,
    private val iClickListener: IClickListener
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : MenuModel?){
        holderBinding.apply {
            setVariable(BR.menuModel, model)
            executePendingBindings()
        }
    }

    init {
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        iClickListener.onClick(adapterPosition,"${holderBinding.text.text}")
                    }catch (e:Exception){
                        e("mException", "에러발생 -> MenuHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}
