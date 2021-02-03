package com.universeindustry.governmentinfo.views.fragments.bank


import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.universeindustry.governmentinfo.MainActivity
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragBankBinding
import com.universeindustry.governmentinfo.online.retrofit.API
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.fragments.bank.dialog.DialogBankDeposit
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.AdapterTypeBank
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
                        adapterTypeBank?.setDepositList(
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
    private var setTabItemString : (Int) -> Unit = { get -> tablayout?.addTab(tablayout?.newTab()?.setText(resources.getString(get))!!) }
    private fun setTablayout(getTablayout : TabLayout){
        getTablayout.let {
            setTabItemString(R.string.bank_type_desposit)
            setTabItemString(R.string.bank_type_saving)
            setTabItemString(R.string.bank_type_annuitySaving)

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
    private var adapterTypeBank: AdapterTypeBank? = null
    private var bankClick : IClickListener? = null
    private fun setRecyclerView(get : RecyclerView){
        try {
            bankClick = object : IClickListener{
                override fun onClick(position : Int, listValueString: Any?) {
                    when(listValueString){
                        is BankDespositModelTree -> {
                            val dialog = DialogBankDeposit(requireContext())
                            dialog.let {
                                it.setData(listValueString)
                                it.show()
                                it.setTitle(listValueString.fin_prdt_nm)
                            }
                        }
                    }
                }
            }

            adapterTypeBank = AdapterTypeBank().apply {
                setClickListener(bankClick!!)
            }

            get.apply {
                adapter = adapterTypeBank
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

/*
  view = TextView(requireContext()).apply {
                                            typeface = ResourcesCompat.getFont(
                                                    requireContext(),
                                                    R.font.notosans_semicondensed_semibold
                                            )
                                            setTextColor(resources.getColor(R.color.colorBlack,null))
                                            textSize = 20f
                                            text = "무엄ㄴ야랴ㅐ재ㅠㅁ아ㅣㅠ먀ㅐ래ㅑ재"
                                        }
 */