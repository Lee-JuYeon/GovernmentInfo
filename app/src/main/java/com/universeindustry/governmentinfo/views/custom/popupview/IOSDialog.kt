package com.universeindustry.governmentinfo.views.custom.popupview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import com.universeindustry.governmentinfo.databinding.DialogIosBinding


class IOSDialog(context : Context) : Dialog(context){

    private lateinit var binding : DialogIosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogIosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setBasicUI()
        setDismiss()
    }
    private fun setBasicUI(){
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setTabbarGradationUI()
        setDialogBackgroundView()
    }
    private fun setDialogBackgroundView(){
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.let {
            it.cornerRadius = 10.0f
            binding.backView.background = it
        }
    }
    private fun setTabbarGradationUI(){
        val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(Color.parseColor("#65ffffff"),
                        Color.parseColor("#ffffff")
                )
        )
        val topLeft = 10.0f
        val topRight = 10.0f
        val bottomRight = 0.0f
        val bottomLeft = 0.0f
        gradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    topLeft,
                    topLeft,
                    topRight,
                    topRight,
                    bottomRight,
                    bottomRight,
                    bottomLeft,
                    bottomLeft
            )
            binding.tabbarContainer.background = it
        }
    }

    fun setTitle(title : String?){
        val getTitle = title ?: " "
        binding.title.text = getTitle
    }
    fun setChildView(view : View){
        val topLeft = 0.0f
        val topRight = 0.0f
        val bottomRight = 10.0f
        val bottomLeft = 10.0f
        val gradientDrawable = GradientDrawable()
        gradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    topLeft,
                    topLeft,
                    topRight,
                    topRight,
                    bottomRight,
                    bottomRight,
                    bottomLeft,
                    bottomLeft
            )
            it.setColor(Color.parseColor("#bb9496"))
        }

        binding.container.let {
            it.addView(view)
            it.background = gradientDrawable
        }
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
