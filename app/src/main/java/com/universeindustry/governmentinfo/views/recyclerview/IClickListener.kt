package com.universeindustry.governmentinfo.views.recyclerview

import android.view.View

interface IClickListener {
    fun onClick(view : View, position : Int, listValueString : Any?)
}