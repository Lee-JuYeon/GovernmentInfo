package com.universeindustry.governmentinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.universeindustry.governmentinfo.databinding.ActivityMainBinding
import com.universeindustry.governmentinfo.online.retrofit.API
import com.universeindustry.governmentinfo.online.retrofit.RetrofitManager
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.fragments.funding.FundingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewBind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBind.root)

        RetrofitManager.instance.testQuery(
//                        searchTerm = "display=100&pageIndex=1",
                searchTerm = API.textQuery,
                completion = {
                    e("mException", it)
                }
        )

        getFragmentType(Strings.funding)
    }

    private lateinit var _fundingFrag : FundingFragment
    private fun getFragmentType(fragment : String){
        try {
            val manager = supportFragmentManager.beginTransaction()
            when(fragment){
                Strings.funding -> {
                    _fundingFrag = FundingFragment()
                    manager.replace(R.id.framelayout, _fundingFrag).commit()
                }
                Strings.career -> {

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
                    _fundingFrag = FundingFragment()
                    manager.replace(R.id.framelayout, _fundingFrag).commit()
                }
            }
        }catch (e:Exception){
            e("mException", "MainActivity, getFragmentType // Exception : ${e.message}")
        }
    }

}
/*
back key 설정
http://pluu.github.io/blog/android/2019/02/03/androidx-backkey/
 */