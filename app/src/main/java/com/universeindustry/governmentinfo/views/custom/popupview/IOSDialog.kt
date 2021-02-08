package com.universeindustry.governmentinfo.views.custom.popupview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.universeindustry.governmentinfo.R
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

        // 탭바 ui설정
        val tabBarGradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(Color.parseColor("#65ffffff"),
                        Color.parseColor("#ffffff")
                )
        )
        val tabBarTopLeft = 10.0f
        val tabBarTopRight = 10.0f
        val tabBarBottomRight = 0.0f
        val tabBarBottomLeft = 0.0f
        tabBarGradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    tabBarTopLeft,
                    tabBarTopLeft,
                    tabBarTopRight,
                    tabBarTopRight,
                    tabBarBottomRight,
                    tabBarBottomRight,
                    tabBarBottomLeft,
                    tabBarBottomLeft
            )
            binding.tabbarContainer.background = it
        }

        // 백그라운드 설정
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.let {
            it.cornerRadius = 10.0f
            binding.backView.background = it
        }

        // 추가된 뷰 ui 설정
        val childViewTopLeft = 0.0f
        val childViewTopRight = 0.0f
        val childViewBottomRight = 10.0f
        val childViewBottomLeft = 10.0f
        val childViewGradientDrawable = GradientDrawable()
        childViewGradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    childViewTopLeft,
                    childViewTopLeft,
                    childViewTopRight,
                    childViewTopRight,
                    childViewBottomRight,
                    childViewBottomRight,
                    childViewBottomLeft,
                    childViewBottomLeft
            )
            it.setColor(Color.parseColor("#bb9496"))
        }

    }

    fun setTitle(title : String?){
        val getTitle = title ?: " "
        binding.title.text = getTitle
    }

    fun getChildView() : Int{
        return R.id.container
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
/*
viewStub 커스텀 관련 링크
https://gist.github.com/sbelloz/2f5504974b7bfc7d947801a86f8725da
https://colinch4.github.io/2020-12-03/async_ViewStub/
        val viewStub = binding.container.viewStub

        inflatedId = 포함된 파일의 루트 id를 재정의하는데 사용.
                     한마디로, viewStub에 추가된 뷰의 이름을 지어주는 것.


databinding null checing
android:text='@{item.gender != null ? item.gender : @string/male}'
android:text='@{item.gender ?? @string/male}'
android:visibility="@{item.drawable == null ? View.INVISIBLE : View.VISIBLE}"



    private fun ViewStub.deflate(): ViewStub {
        val viewParent = parent

        if (viewParent != null && viewParent is ViewGroup) {
            val viewStub = ViewStub(context).apply {
                inflatedId = this@deflate.inflatedId
                layoutParams = this@deflate.layoutParams
            }
            val index = viewParent.indexOfChild(this)

            viewParent.removeView(this)
            viewParent.addView(viewStub, index)
            return viewStub
        } else {
            throw IllegalStateException("Inflated View has not a parent")
        }
    }

 */
