package com.universeindustry.governmentinfo.views.fragments.menu.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderMenuBinding

class MenuHolder(
    private val holderBinding : HolderMenuBinding
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : MenuModel){
        holderBinding.apply {
            setVariable(BR.menuModel, model)
            executePendingBindings()
        }
    }
}
