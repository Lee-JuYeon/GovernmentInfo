package com.universeindustry.governmentinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universeindustry.governmentinfo.utils.extensions.Strings

class MainViewModel : ViewModel(){
    private val _fragmentType = MutableLiveData<String>("")
    val setFragmentType: LiveData<String> = _fragmentType


    init {
        _fragmentType.value = Strings.menu

    }

}