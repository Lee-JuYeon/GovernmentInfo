package com.universeindustry.governmentinfo.views.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universeindustry.governmentinfo.utils.extensions.SingleLiveEvent
import com.universeindustry.governmentinfo.views.fragments.funding.FundingFragment

class FragmentVM : ViewModel(){

    private val _funding : String = "지원금"
    private lateinit var _fundingFrag : FundingFragment

    private val _career : String = "스펙, 자격증"
    private val _medical : String = "의료보험"
    private val _jobSearching : String = "구인구직"
    private val _realEstate : String = "주거"
    private val _banking : String = "적금추천"


    private val _fragmentType : MutableLiveData<String> = MutableLiveData<String>()
    var getFragmentType : LiveData<String>
        get(){
            return _fragmentType
        }
        set(value){
            return when(value.value){
                _funding -> {

                }
                _career -> {

                }
                _medical -> {

                }
                _jobSearching -> {

                }
                _realEstate -> {

                }
                _banking -> {

                }
                else -> {

                }
            }
        }


    init {

    }
}