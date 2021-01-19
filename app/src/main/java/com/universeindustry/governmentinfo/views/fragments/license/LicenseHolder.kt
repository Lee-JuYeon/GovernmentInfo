package com.universeindustry.governmentinfo.views.fragments.license

import android.util.Log.e
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
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        iClickListener?.onClick(adapterPosition,"${holderBinding.title.text}")
                    }catch (e:Exception){
                        e("mException", "에러발생 -> LicenseHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}
