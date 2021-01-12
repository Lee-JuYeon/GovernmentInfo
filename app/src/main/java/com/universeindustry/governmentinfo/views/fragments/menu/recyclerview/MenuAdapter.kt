package com.universeindustry.governmentinfo.views.fragments.menu.recyclerview

import android.util.Log.e
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R

class MenuAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val menuList = arrayListOf(
        MenuModel(
            image = R.drawable.image_bank,
            title = "지원금"),
        MenuModel(
            image = R.drawable.image_bank,
            title = "주거, 금융"),
        MenuModel(
            image = R.drawable.image_bank,
            title = "교외활동"),
        MenuModel(
            image = R.drawable.image_bank,
            title = "자격증"),
        MenuModel(
            image = R.drawable.image_bank,
            title = "회사 알아보기"),
        MenuModel(
            image = R.drawable.image_bank,
            title = "의료 서비스")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MenuHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_menu, parent,false))
    override fun getItemCount(): Int = menuList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MenuHolder){
            val dataList = menuList[position]
            holder.dataBinding(dataList)
        }else{
            val exception = Exception()
            throw Exception("MenuAdapter, onBindViewHolder // Exception : ${exception.message}")
        }
    }

//    @BindingAdapter("app:setImage")
//    fun setImage(view: ImageView, image: Int) {
//        view.setImageResource(image)
//    }

    fun setInFun(txtTest : String?){
        e("mException", "클린된 것 : ${txtTest}")
    }
}