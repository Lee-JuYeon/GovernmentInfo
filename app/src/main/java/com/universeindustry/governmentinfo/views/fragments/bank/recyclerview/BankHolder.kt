package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderBankBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class BankHolder(
        private val holderBinding: HolderBankBinding,
        private val iClickListener: IClickListener
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : Any?){
        holderBinding.apply {
            setVariable(BR.bankModel, model as BankModel)
            executePendingBindings()
        }
    }

    init {
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        iClickListener.onClick(adapterPosition,"${holderBinding.title}")
                    }catch (e:Exception){
                        Log.e("mException", "에러발생 -> BankHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}
