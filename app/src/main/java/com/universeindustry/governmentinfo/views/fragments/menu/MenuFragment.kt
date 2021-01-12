package com.universeindustry.governmentinfo.views.fragments.menu

import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.FragMenuBinding
import com.universeindustry.governmentinfo.views.fragments.menu.recyclerview.MenuAdapter

class MenuFragment : Fragment(){
    private lateinit var binding: FragMenuBinding
    //----------------------------------------- 뷰 생성시  --------------------------------------------------------------------//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_menu, container, false)
        }catch (e:Exception){
            e("mException", "에러발생 -> MenuFragment, onCreateView // Exception : ${e.message}")
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
            e("mException", "에러발생 -> MenuFragment, onViewCreated // Exception : ${e.message}")
        }finally {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    //----------------------------------------- 액티비티 생성 완료 시 --------------------------------------------------------------------//
    private val menuVM : MenuVM by viewModels()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            activity?.let {
                binding.apply {
                    menuVM = menuVM
                    lifecycleOwner = this@MenuFragment
                }
                menuVM.let {

                }
            }
        }catch (e:Exception){
            e("mException", "에러발생 : MenuFragment, onActivityCreated  // Exception : ${e.message}")
        }finally {
            super.onActivityCreated(savedInstanceState)
        }
    }


    //----------------------------------------- 리사이클러뷰 설정 ---------------------------------------------//
    private var menuAdapter: MenuAdapter? = null
    fun setRecyclerView(get : RecyclerView){
        try {
            menuAdapter = MenuAdapter().apply {
            }

            get.apply {
                adapter = MenuAdapter()
                layoutManager = GridLayoutManager(context, 3).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                setHasFixedSize(true)
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            e("mException", "MenuFragment, setRecyclerView // Exception : ${e.message}")
        }
    }

}