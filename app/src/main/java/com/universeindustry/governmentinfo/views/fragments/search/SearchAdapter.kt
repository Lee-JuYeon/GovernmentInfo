package com.universeindustry.governmentinfo.views.fragments.search


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.HolderBankBinding
import com.universeindustry.governmentinfo.databinding.HolderFundingBinding
import com.universeindustry.governmentinfo.databinding.HolderLicenseBinding
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.BankHolder
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.BankModel
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingHolder
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingModel
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseHolder
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseModel
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener
import com.universeindustry.governmentinfo.utils.extensions.Strings


class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var customList : ArrayList<Any>? = arrayListOf()
    private var fragmentType : String? = null
    fun setFragmentType(get : String, newList : List<Any>){
        // 타입 업데이트
        this.fragmentType = get

        // 리스트 업데이트
        val diffResult = DiffUtil.calculateDiff(SearchDiffUtil(customList ?: arrayListOf(), newList, get), false)
        diffResult.dispatchUpdatesTo(this@SearchAdapter)
        customList?.clear()
        customList?.addAll(newList)
    }

    private var iClickListener : IClickListener? = null
    fun setClickListener(get : IClickListener?){ this.iClickListener = get }

    private val typeFunding : Int = 0
    private val typeLicense : Int = 1
    private val typeBank : Int = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val bankBinding = HolderBankBinding.inflate(layoutInflater, parent, false)
        val fundBinding = HolderFundingBinding.inflate(layoutInflater, parent, false)
        val licenseBinding = HolderLicenseBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            typeFunding -> FundingHolder(fundBinding, iClickListener!!)
            typeLicense -> LicenseHolder(licenseBinding, iClickListener!!)
            typeBank -> BankHolder(bankBinding, iClickListener!!)
            else -> FundingHolder(fundBinding, iClickListener!!)
        }
    }
    override fun getItemCount(): Int {
        return when(fragmentType){
            Strings.funding-> customList?.size ?:0
            Strings.license-> customList?.size ?:0
            Strings.house-> 0
            Strings.bank-> customList?.size ?:0
            else -> customList?.size ?:0
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BankHolder -> {
                val dataList = customList!![position]
                holder.dataBinding(
                        model = dataList
                )
            }
            is FundingHolder -> {
                val dataList = customList!![position]
                holder.dataBinding(
                        model = dataList
                )
            }
            is LicenseHolder -> {
                val dataList = customList!![position]
                holder.dataBinding(
                        model = dataList
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("SearchAdapter, onBindViewHolder // Exception : ${exception.message}")
            }
        }
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when(holder){
            is BankHolder -> {
                holder.dataBinding(
                        model = null
                )
            }
            is FundingHolder -> {
                holder.dataBinding(
                        model = null
                )
            }
            is LicenseHolder -> {
                holder.dataBinding(
                        model = null
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("SearchAdapter, onViewRecycled // Exception : ${exception.message}")
            }
        }
        super.onViewRecycled(holder)
    }
    override fun getItemViewType(position: Int): Int {
        return when(fragmentType){
            Strings.funding-> typeFunding
            Strings.license-> typeLicense
            Strings.bank-> typeBank
            else -> typeFunding
        }
    }
}