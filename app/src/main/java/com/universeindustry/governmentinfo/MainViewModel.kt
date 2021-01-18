package com.universeindustry.governmentinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universeindustry.governmentinfo.utils.extensions.Strings

class MainViewModel : ViewModel(){
    private val _fragmentType = MutableLiveData<String>("")
    val getFragmentType: LiveData<String>
        get() = _fragmentType

    fun setFragmentType(type : String){
        this._fragmentType.value = type
    }

    init {
        _fragmentType.value = Strings.menu

    }

}