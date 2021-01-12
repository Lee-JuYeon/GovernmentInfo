package com.universeindustry.governmentinfo.views.fragments.funding

import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragFundingBinding
import com.universeindustry.governmentinfo.online.retrofit.RetrofitClient
import com.universeindustry.governmentinfo.online.retrofit.RetrofitManager
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingAdapter

class FundingFragment : Fragment(){

    private lateinit var binding: FragFundingBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_funding, container, false)
        }catch (e:Exception){
            Log.e("mException", "에러발생 -> FragmentFunding, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    //----------------------------------------- 뷰 생성 완료 시 --------------------------------------------------------------------//
    private var recyclerView : RecyclerView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            recyclerView = binding.fundingRecyclerview
            setRecyclerView(recyclerView!!)

        }catch (e:Exception){
            Log.e(
                "mException",
                "에러발생 -> FragmentFunding, onViewCreated // Exception : ${e.message}"
            )
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 액티비티 생성 완료 시 --------------------------------------------------------------------//
    private val _fundingVM : FundingVM by viewModels()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            activity?.let {
                binding.apply {
                    viewmodel = _fundingVM
                    lifecycleOwner = this@FundingFragment
                }
                _fundingVM.let {
                    it._currentText.observe(context as LifecycleOwner, Observer {
                    })
                }
            }
        }catch (e:Exception){
            Log.e(
                "mException",
                "에러발생 : FragmentFunding, onActivityCreated  // Exception : ${e.message}"
            )
        }finally {
            super.onActivityCreated(savedInstanceState)
        }
    }


    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var fundingAdapter: FundingAdapter? = null
    fun setRecyclerView(get : RecyclerView){
        try {
            fundingAdapter = FundingAdapter().apply {
            }

            get.apply {
                adapter = FundingAdapter()
                layoutManager = LinearLayoutManager(context).apply {
                    isItemPrefetchEnabled = false
                }
                setHasFixedSize(true)
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            Log.e("mException", "FundingVM, setRecyclerView // Exception : ${e.message}")
        }
    }
}