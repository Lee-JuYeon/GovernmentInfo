package com.universeindustry.governmentinfo.views.fragments.search


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
import com.universeindustry.governmentinfo.MainActivity
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragSearchBinding
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.BankModel
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingModel
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseModel
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class SearchFragment : Fragment(){
    private lateinit var binding: FragSearchBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_search, container, false)
        }catch (e:Exception){
            e("mException", "에러발생 -> SearchFragment, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    //----------------------------------------- 뷰 생성 완료 시 --------------------------------------------------------------------//
    private var recyclerView : RecyclerView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            recyclerView = binding.recyclerview
            setRecyclerView(recyclerView!!)
        }catch (e:Exception){
            e("mException", "에러발생 -> SearchFragment, onViewCreated // Exception : ${e.message}")
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 액티비티 생성 완료 시 --------------------------------------------------------------------//
    private val _SearchVM : SearchVM by viewModels()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            activity?.let {
                binding.apply {
                    searchVM = _SearchVM
                    lifecycleOwner = this@SearchFragment
                }

                val fragmentType : String = (activity as MainActivity).mainVM.getFragmentType.value!!
                _SearchVM.let {
                    it.searchList.observe(activity as MainActivity, Observer {
                        when(fragmentType){
                            Strings.funding-> {
                                searchAdapter?.setFragmentType(
                                        get = fragmentType,
                                        newList = arrayListOf(
                                                FundingModel(
                                                        image = R.drawable.background_all_radius,
                                                        title = "타이틀11",
                                                        date = "지원금 타이틀 11",
                                                        money = "1won",
                                                        option = "option 1"
                                                ),
                                                FundingModel(
                                                        image = R.drawable.background_all_radius,
                                                        title = "지원금 타이틀 22",
                                                        date = "date 22",
                                                        money = "1won",
                                                        option = "option 1"
                                                ),
                                                FundingModel(
                                                        image = R.drawable.background_all_radius,
                                                        title = "지원금 타이틀 33",
                                                        date = "date 33",
                                                        money = "1won",
                                                        option = "option 1"
                                                )
                                        )
                                )
                            }
                            Strings.license-> {
                                searchAdapter?.setFragmentType(
                                        get = fragmentType,
                                        newList = arrayListOf(
                                                LicenseModel(
                                                        title = "자격증 타이틀1",
                                                        examFee = "만원",
                                                        writeenTestDate = hashMapOf(),
                                                        practicalTestDate = hashMapOf()
                                                ),
                                                LicenseModel(
                                                        title = "자격증 타이틀2",
                                                        examFee = "만원",
                                                        writeenTestDate = hashMapOf(),
                                                        practicalTestDate = hashMapOf()
                                                )
                                        )
                                )
                            }
                            Strings.bank-> {
                                searchAdapter?.setFragmentType(
                                        get = fragmentType,
                                        newList = arrayListOf(
                                                BankModel(
                                                        title = "은행 타이틀1",
                                                        type = "유형:단기",
                                                        saving = "저축금리",
                                                        vip = "우대금리"
                                                ),
                                                BankModel(
                                                        title = "은행 타이틀2",
                                                        type = "유형:단기",
                                                        saving = "저축금리",
                                                        vip = "우대금리"
                                                )
                                        )
                                )
                            }
                        }
                    })
                }
            }
        }catch (e:Exception){
            e("mException", "에러발생 : SearchFragment, onActivityCreated  // Exception : ${e.message}")
        }finally {
            super.onActivityCreated(savedInstanceState)
        }
    }


    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var searchAdapter: SearchAdapter? = null
    private var licenseClick : IClickListener? = null
    fun setRecyclerView(get : RecyclerView){
        try {
            licenseClick = object : IClickListener{
                override fun onClick(position : Int, listValueString: Any?) {
                    e("mException", "눌린 것 : ${listValueString as String}")
                }
            }

            searchAdapter = SearchAdapter().apply {
                setClickListener(licenseClick!!)
            }

            get.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                setHasFixedSize(true)
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            e("mException", "SearchFragment, setRecyclerView // Exception : ${e.message}")
        }
    }
}