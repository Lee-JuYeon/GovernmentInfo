package com.universeindustry.governmentinfo.views.popup.bank

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.DialogBankDepositBinding


class BankDetailDialog(context : Context) : Dialog(context){

    private lateinit var binding : DialogBankDepositBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogBankDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onStart() {
        super.onStart()
        setDismiss()
    }

    private fun setDismiss(){
        binding.dismiss.let {
            it.setOnClickListener {
                if (this.isShowing){
                    this.dismiss()
                }
            }
        }
    }
}

