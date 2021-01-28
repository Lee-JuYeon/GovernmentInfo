package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.BR
import com.universeindustry.governmentinfo.databinding.HolderBankBinding
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class DespositHolder(
        private val holderBinding: HolderBankBinding,
        private val iClickListener: IClickListener
) : RecyclerView.ViewHolder(holderBinding.root){
    fun dataBinding(model : BankDespositModelTree?){
        holderBinding.apply {
            setVariable(BR.bankModel, model)
            executePendingBindings()
        }
    }

    init {
        holderBinding.background.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    try {
                        Log.e("mException", "CLICKED: ${holderBinding.title.text}")

                        iClickListener.onClick(adapterPosition,"${holderBinding.title.text}")
                    }catch (e:Exception){
                        Log.e("mException", "에러발생 -> BankHolder, init, setOnClickListener // Exception : ${e.message}")
                    }
                }
            }
        }
    }
}
