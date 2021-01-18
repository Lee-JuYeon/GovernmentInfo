package com.universeindustry.governmentinfo.views.fragments.license


import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragLicenseBinding
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener

class LicenseFragment : Fragment(){
    private lateinit var binding: FragLicenseBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_license, container, false)
        }catch (e:Exception){
            e("mException", "에러발생 -> LicenseFragment, onCreateView // Exception : ${e.message}")
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
            e("mException", "에러발생 -> LicenseFragment, onViewCreated // Exception : ${e.message}")
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 액티비티 생성 완료 시 --------------------------------------------------------------------//
    private val licenseVM : LicenseVM by viewModels()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            activity?.let {
                binding.apply {
                    licenseVM = licenseVM
                    lifecycleOwner = this@LicenseFragment
                }
                licenseVM.let {

                }
            }
        }catch (e:Exception){
            e("mException", "에러발생 : LicenseFragment, onActivityCreated  // Exception : ${e.message}")
        }finally {
            super.onActivityCreated(savedInstanceState)
        }
    }


    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var licenseAdapter: LicenseAdapter? = null
    private var licenseClick : IClickListener? = null
    fun setRecyclerView(get : RecyclerView){
        try {
            licenseClick = object : IClickListener{
                override fun onClick(listValueString: Any?) {
                    e("mException", "눌린 것 : ${listValueString as String}")
                    e("mException", "눌")
                }
            }

            licenseAdapter = LicenseAdapter().apply {
                setClickListener(licenseClick!!)
            }

            get.apply {
                adapter = LicenseAdapter()
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                setHasFixedSize(true)
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            e("mException", "LicenseFragment, setRecyclerView // Exception : ${e.message}")
        }
    }

}