package com.universeindustry.governmentinfo.views.fragments.bank.layout

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragBankDepositBinding
import com.universeindustry.governmentinfo.databinding.LayoutBankDepositBinding
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.custom.popupview.IOSDialog
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.AdapterTypeBank
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.ChartDeposit.AdapterDeposit
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class FragmentDeposit(private var data : BankDespositModelTree?) : Fragment() {

    private lateinit var binding : FragBankDepositBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_bank_deposit, container, false)
        }catch (e:Exception){
            Log.e("mException", "에러발생 -> FragmentDeposit, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    //----------------------------------------- 뷰 생성 완료 시 --------------------------------------------------------------------//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.text.text = "asdfasdfasdfasdfasdf"
            setRecyclerView(binding.recyclerview)
        }catch (e:Exception){
            Log.e("mException", "에러발생 -> FragmentDeposit, onViewCreated // Exception : ${e.message}")
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var adapterDeposit : AdapterDeposit? = null
    private fun setRecyclerView(get : RecyclerView){
        try {
            if (adapterDeposit == null) adapterDeposit = AdapterDeposit()

            get.apply {
                adapterDeposit?.setList(data?.optionListItem ?: arrayListOf())
                adapter = adapterDeposit
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                setItemViewCacheSize(0)
                setHasFixedSize(true)

            }
        }catch (e:Exception){
            Log.e("mException", "FragmentDeposit, setRecyclerView // Exception : ${e.message}")
        }
    }
}

/*

    private var requireContext : Context? = null
    fun setRequireContext(context : Context){
        this.requireContext = context
    }

    private var getData : BankDespositModelTree? = null
    fun getData(data : BankDespositModelTree){
        this.getData = data
    }

    private var adapterDeposit : AdapterDeposit? = null
    init {
        adapterDeposit = AdapterDeposit()
    }

    private lateinit var bind : LayoutBankDepositBinding
    fun setView() : Fragment {
        bind = DataBindingUtil.setContentView(requireContext as Activity, R.layout.layout_bank_deposit)
        bind.background.layoutParams.let {
            it.width = RelativeLayout.LayoutParams.WRAP_CONTENT
            it.height = RelativeLayout.LayoutParams.WRAP_CONTENT
        }
        bind.recyclerview.apply {
            adapterDeposit?.setList(getData?.optionListItem ?: arrayListOf())
            adapter = adapterDeposit
            layoutManager = LinearLayoutManager(requireContext).apply {
                orientation = LinearLayoutManager.HORIZONTAL
                isItemPrefetchEnabled = false
                setItemViewCacheSize(0)
            }
        }
        return bind
    }

 */