package com.universeindustry.governmentinfo.views.fragments.license

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderLicenseBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class LicenseHolder(
    private val holderBinding: HolderLicenseBinding,
    private val iClickListener: IClickListener?
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : LicenseModel?){
        holderBinding.apply {
            setVariable(BR.licenseModel, model)
            executePendingBindings()
        }
    }

    init {

    }
}
