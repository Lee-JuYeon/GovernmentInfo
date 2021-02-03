package com.universeindustry.governmentinfo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.universeindustry.governmentinfo.databinding.ActivityMainBinding
import com.universeindustry.governmentinfo.online.retrofit.RetrofitCallingManager
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.fragments.bank.BankFragment
import com.universeindustry.governmentinfo.views.fragments.menu.MenuFragment
import com.universeindustry.governmentinfo.views.fragments.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewBind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBind.root)

        setMainVM()


        supportFragmentManager.beginTransaction()
    }

    val mainVM : MainViewModel by viewModels()
    private lateinit var _menuFrag : MenuFragment
    private var _searchFrag : SearchFragment? = null
    private var _bankFrag : BankFragment? = null
    private fun setMainVM(){
        mainVM.getFragmentType.observe(this, Observer {
            val manager = supportFragmentManager.beginTransaction()
            when(it){
                Strings.menu -> {
                    _menuFrag = MenuFragment()
                    manager.replace(R.id.framelayout, _menuFrag)
//                        .addToBackStack(null)
                        .commit()
                }
                Strings.funding -> {
                    if (_searchFrag == null) _searchFrag = SearchFragment()
                    _searchFrag = SearchFragment()
                    manager.replace(R.id.framelayout, _searchFrag!!)
                        .commit()
                }
                Strings.license -> {
                    if (_searchFrag == null) _searchFrag = SearchFragment()
                    _searchFrag = SearchFragment()
                    manager.replace(R.id.framelayout, _searchFrag!!)
                            .commit()
                }
                Strings.bank -> {
                    if (_bankFrag == null) _bankFrag = BankFragment()
                    _bankFrag = BankFragment()
                    manager.replace(R.id.framelayout, _bankFrag!!)
                            .commit()
                }
                Strings.vaccine -> {

                }
                Strings.house -> {

                }
                else -> {
                    _menuFrag = MenuFragment()
                    manager.replace(R.id.framelayout, _menuFrag)
                        .commit()
                }
            }
        })
    }
}
/*
back key 설정
http://pluu.github.io/blog/android/2019/02/03/androidx-backkey/
 */