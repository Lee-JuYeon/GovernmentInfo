package com.universeindustry.governmentinfo.views.fragments.bank

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universeindustry.governmentinfo.online.retrofit.RetrofitCallingManager
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.utils.extensions.Strings


class BankVM() : ViewModel() {
    private val _fragmentType = MutableLiveData<String>("")
    val getFragmentType: LiveData<String>
        get() = _fragmentType

    fun setFragmentType(type : String){
        this._fragmentType.value = type
    }

    private val _despositList = MutableLiveData<ArrayList<BankDespositModelTree>>(arrayListOf())
    val getDespositList : LiveData<ArrayList<BankDespositModelTree>>
        get() = _despositList

    private fun getBankListData(){
        // 테스트 쿼리 불러오는 곳입니다. 쿼리는 online-retrofit-API의 textQuery를 불러왔습니다.
        RetrofitCallingManager.instance.let {
            it.getBankListData {
                _despositList.postValue(it)
            }
        }
    }

    private val type1 = "정기예금"
    private val type2 = "적금"
    private val type3 = "연금저축"

    //초기 값 설정
    init {
        getBankListData()
    }


    override fun onCleared() {
        super.onCleared()
        try {

        }catch (e:Exception){
            Log.e("mException", "BankVM, onCleared // Exception : ${e.message}")
        }
    }
}

