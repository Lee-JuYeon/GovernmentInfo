package com.universeindustry.governmentinfo.views.fragments.bank


import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.universeindustry.governmentinfo.MainActivity
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragBankBinding
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.BankAdapter
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class BankFragment : Fragment(){
    private lateinit var binding: FragBankBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_bank, container, false)
        }catch (e:Exception){
            e("mException", "에러발생 -> BankFragment, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    //----------------------------------------- 뷰 생성 완료 시 --------------------------------------------------------------------//
    private var recyclerView : RecyclerView? = null
    private var tablayout : TabLayout? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            recyclerView = binding.recyclerview
            tablayout = binding.tablayout

            setTablayout(binding.tablayout)
            setRecyclerView(binding.recyclerview)
        }catch (e:Exception){
            e("mException", "에러발생 -> BankFragment, onViewCreated // Exception : ${e.message}")
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 액티비티 생성 완료 시 --------------------------------------------------------------------//
    private val _BankVM : BankVM by viewModels()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            activity?.let {
                binding.apply {
                    bankVM = _BankVM
                    lifecycleOwner = this@BankFragment
                }

                _BankVM.let {
                    it.getDespositList.observe(activity as MainActivity, Observer {
                        bankAdapter?.setRecyclerviewType(
//                                get = tablayout?.get(tab),
                                get = API.desposit,
                                newList = it
                        )
                    })
                }
            }
        }catch (e:Exception){
            e("mException", "에러발생 : BankFragment, onActivityCreated  // Exception : ${e.message}")
        }finally {
            super.onActivityCreated(savedInstanceState)
        }
    }
    //----------------------------------------- Tablayout 설정 ---------------------------------------------//
    private fun setTablayout(getTablayout : TabLayout){
        getTablayout.let {
            it.addTab(it.newTab().setText(requireContext().resources.getText(R.string.bank_type_desposit)))
            it.addTab(it.newTab().setText(requireContext().resources.getText(R.string.bank_type_saving)))
            it.addTab(it.newTab().setText(requireContext().resources.getText(R.string.bank_type_annuitySaving)))

            it.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position ?: 0
                }
            })
        }
    }


    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var bankAdapter: BankAdapter? = null
    private var bankClick : IClickListener? = null
    private fun setRecyclerView(get : RecyclerView){
        try {
            bankClick = object : IClickListener{
                override fun onClick(position : Int, listValueString: Any?) {
                    e("mException", "눌린 것 : ${listValueString as String}")
                }
            }

            bankAdapter = BankAdapter().apply {
                setClickListener(bankClick!!)
            }

            get.apply {
                adapter = bankAdapter
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                setHasFixedSize(true)
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            e("mException", "BankFragment, setRecyclerView // Exception : ${e.message}")
        }
    }
}