package com.universeindustry.governmentinfo.views.recyclerview

import android.util.Log
import android.view.View

interface IClickListener {
    fun onClick(position : Int ,listValueString : Any?)
}