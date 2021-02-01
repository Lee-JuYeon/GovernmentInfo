package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderBankDespositBinding
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class HolderTypeDeposit(
        private val holderBinding: HolderBankDespositBinding,
        private val iClickListener: IClickListener
) : RecyclerView.ViewHolder(holderBinding.root){

    private var bankDespositModel : BankDespositModelTree? = null
    fun dataBinding(model : BankDespositModelTree?){
        holderBinding.apply {
            bankDespositModel = model
            setVariable(BR.bankModel, model)
            executePendingBindings()
        }
    }

    init {
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        iClickListener.onClick(adapterPosition,bankDespositModel)
                    }catch (e:Exception){
                        Log.e("mException", "에러발생 -> BankHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}
