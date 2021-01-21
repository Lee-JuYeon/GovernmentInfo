package com.universeindustry.governmentinfo.views.fragments.bank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class BankVM() : ViewModel() {
    val _currentText = MutableLiveData<String>()

//    private var fundingAdapter: FundingAdapter? = null
//    fun setRecyclerView(get : RecyclerView){
//        try {
//            fundingAdapter = FundingAdapter().apply {
//                Log.e("mException", "fundingAdapter")
//            }
//
//            get.apply {
//                Log.e("mException", "RecyclerView")
//
//                adapter = fundingAdapter
//                setHasFixedSize(true)
//                setItemViewCacheSize(0)
//            }
//        }catch (e:Exception){
//            Log.e("mException", "FundingVM, setRecyclerView // Exception : ${e.message}")
//        }
//    }

    //초기 값 설정
    init {
        _currentText.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        try {
            _currentText.value = ""
//            fundingAdapter = null
        }catch (e:Exception){
            Log.e("mException", "FundingVM, onCleared // Exception : ${e.message}")
        }
    }
}

