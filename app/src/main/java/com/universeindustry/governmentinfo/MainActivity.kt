package com.universeindustry.governmentinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.universeindustry.governmentinfo.databinding.ActivityMainBinding
import com.universeindustry.governmentinfo.online.retrofit.RetrofitCallingManager
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.fragments.funding.FundingFragment
import com.universeindustry.governmentinfo.views.fragments.license.LicenseFragment
import com.universeindustry.governmentinfo.views.fragments.menu.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewBind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBind.root)

        // 테스트 쿼리 불러오는 곳입니다. 쿼리는 online-retrofit-API의 textQuery를 불러왔습니다.
//        RetrofitCallingManager.instance.testQuery(
//                searchTerm = API.mQuery,
//                completion = {
//                    e("mException", it)
//                }
//        )
//
//        RetrofitCallingManager.instance.getLicenseList(
//                completion = {
//                    e("mException", " completion : ${it} ")
//                }
//        )
        setMainVM()

    }

    val mainVM : MainViewModel by viewModels()
    private lateinit var _menuFrag : MenuFragment
    private lateinit var _fundingFrag : FundingFragment
    private lateinit var _licenseFrag : LicenseFragment
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
                    _fundingFrag = FundingFragment()
                    manager.replace(R.id.framelayout, _fundingFrag)
//                        .addToBackStack(null)
                        .commit()
                }
                Strings.license -> {
                    _licenseFrag = LicenseFragment()
                    manager.replace(R.id.framelayout, _licenseFrag)
//                        .addToBackStack(null)
                        .commit()
                }
                Strings.medical -> {

                }
                Strings.jobSearching -> {

                }
                Strings.realEstate -> {

                }
                Strings.banking -> {

                }
                else -> {
                    _menuFrag = MenuFragment()
                    manager.replace(R.id.framelayout, _menuFrag)
//                        .addToBackStack(null)
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