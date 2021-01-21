package com.universeindustry.governmentinfo.views.fragments.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.BankModel
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingModel
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseModel
import java.util.*
import kotlin.collections.ArrayList

class SearchVM : ViewModel(){
    /*
    ViewModel :
        viewmodel에는 최대한 android framework가 최대한 없는 방향으로 코드를 작성해야한다.
        - 메모리 leak 유발하는 요소
            1. activity의 class(context 참조하는 경우), view, context는 lifecycle 길이상 맞지 않는다.
        view를 참조하고 싶을때는 LiveData observing을 이용하여 view를 update한다.

    Disposable :
        Disposable은 Observer가 Observable을 구독할 때 발행되는 객체이다.
        또한 Disposable의 인터페이스인 dispose()를 사용함으로써 구독을 해제해 메모리 누수를 방지할 수 있다.
        CompositeDisposable을 사용하여 모든 Disposable 구독을 ViewModel에서 해제해준다.

    ViewModel 사용시 주의사항 :
        context를 사용하시 viewmodel의 라이프 사이클이 더 길어서 메모리 누수가 생긴다. 라이프 사이클 길이 대조해보면 앞뒤가 안맞음.
        CompositeDisposable을 사용하여 UseCase에서 발행된 Disposable을 관리한다.

    MediatorLiveData :
     - 개념
        1) 여러 개의 LiveData를 관찰할 수 있으며 MediatorLiveData 자신 또한 관찰의 대상이 되어야 정상적으로 동작.
     - 링크
        1) https://velog.io/@hkg5600/%EA%B0%84%EB%8B%A8%ED%95%9C-%EB%8F%84%EC%84%9C-%EA%B2%80%EC%83%89-%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%95%B1-%EB%A7%8C%EB%93%A4%EA%B8%B0-3%ED%8E%B8-UseCase%EC%99%80-ViewModel

    RecyclerView adapter xml :
     - 링크
        1) https://velog.io/@hkg5600/%EA%B0%84%EB%8B%A8%ED%95%9C-%EB%8F%84%EC%84%9C-%EA%B2%80%EC%83%89-%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%95%B1-%EB%A7%8C%EB%93%A4%EA%B8%B0-4%ED%8E%B8-%EB%81%9D

    postValue vs value
        Livedata.postValue("a")
            - 백그라운드에 진행.
            - postValue 이후에 바로 value를 호출할 시, 변경된 값을 받아오지 못할 가능성이 크다.
                반면, value로 값을 변경하면 메인쓰레드에서 변경하는 것이기 떄문에 바로 다음 라인에서 value로 변경된 값을 읽어올 수 있다.

        Livedata.value("b")
            - 메인 쓰레드에서 그 즉시 값이 반영된다(메인 쓰레드에서 값을 dispatch 시킨다.)
     */

    val _currentText = MutableLiveData<String>()

    private val _list = MutableLiveData<ArrayList<Any>>()
    val searchList : LiveData<ArrayList<Any>>
        get() = _list


    //초기 값 설정
    init {
        _currentText.value = ""
        _list.value = arrayListOf()
    }

    /*
    ViewModel 종료시에 호출된다.
    해당 구간에서 모든 Disposable의 구독을 해제한다.
     */
    override fun onCleared() {
        super.onCleared()
        try {
            _currentText.value = ""
            _list.value = arrayListOf()
        }catch (e:Exception){
            Log.e("mException", "SearchVM, onCleared // Exception : ${e.message}")
        }
    }
}