package com.universeindustry.governmentinfo.views.fragments.funding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingAdapter


class FundingVM() : ViewModel() {
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



/*
  private val _currentText = MutableLiveData<String>()
    val currentText : LiveData<String>
        get() = _currentText

    var doOnTextChanged: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    //초기 값 설정
    init {
        _currentText.value = ""
    }


    @Bindable var searchKeyord : String? = null
        set(value) {
            if (field != value) {
                field = value
                onSearchEntered(value)
                notifyPropertyChanged(BR.searchKeyword)
            }
        }


    inner class EditTextBindingAdapters {

        @BindingAdapter("textChangedListener")
        fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
            editText.addTextChangedListener(textWatcher)
        }


    }


    companion object {

        @BindingAdapter("text")
        @JvmStatic
        fun bindEditText(view: AppCompatEditText, bindableString: BindableString){
            val pair = view.getTag(R.id.bound_observable) as Pair<BindableString, MyTextWatcherAdapter?>?
            if (pair == null || pair.first != BindableString()){
                if (pair != null) {
                    view.removeTextChangedListener(pair.second as TextWatcher)
                }
                val watcher = object: MyTextWatcherAdapter(){
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        super.onTextChanged(s, start, before, count)
                        bindableString.set(s?.toString())
                    }
                }
                view.setTag(R.id.bound_observable, Pair<BindableString, MyTextWatcherAdapter>(bindableString, watcher))
                view.addTextChangedListener(watcher)
            }
            val newValue = bindableString.get()
            if (view.text?.toString() != newValue) {
                view.setText(newValue)
            }

        }

    }

    @Bindable
    val optionSearch = MutableLiveData<String>()

    init {
        optionSearch.value = null
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //@Bindable build error fix
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //@Bindable build error fix
    }

 */

