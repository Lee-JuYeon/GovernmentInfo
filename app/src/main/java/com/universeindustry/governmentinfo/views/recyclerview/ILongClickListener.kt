package com.universeindustry.governmentinfo.views.recyclerview


import android.view.View

interface ILongClickListener {
    fun onLong(view : View, position : Int, listValueString : Any?)
}