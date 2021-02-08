package com.universeindustry.governmentinfo.views.fragments.search


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.databinding.HolderFundingBinding
import com.universeindustry.governmentinfo.databinding.HolderLicenseBinding
import com.universeindustry.governmentinfo.views.fragments.funding.recyclerview.FundingHolder
import com.universeindustry.governmentinfo.views.fragments.license.recyclerview.LicenseHolder
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener
import com.universeindustry.governmentinfo.utils.extensions.Strings
import com.universeindustry.governmentinfo.views.base.BaseDiffUtil


class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var customList : ArrayList<Any>? = arrayListOf()
    private var fragmentType : String? = null
    fun setFragmentType(get : String, newList : ArrayList<Any>){
        // 타입 업데이트
        this.fragmentType = get

        // 리스트 업데이트
        val diffResult = DiffUtil.calculateDiff(
                BaseDiffUtil(
                        oldList = customList ?: arrayListOf(),
                        currentList = newList,
                        type = get
                ), false)
        diffResult.dispatchUpdatesTo(this@SearchAdapter)
        customList?.clear()
        customList?.addAll(newList)
    }

    private var iClickListener : IClickListener? = null
    fun setClickListener(get : IClickListener?){ this.iClickListener = get }

    private val typeFunding : Int = 0
    private val typeLicense : Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val fundBinding = HolderFundingBinding.inflate(layoutInflater, parent, false)
        val licenseBinding = HolderLicenseBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            typeFunding -> FundingHolder(fundBinding, iClickListener!!)
            typeLicense -> LicenseHolder(licenseBinding, iClickListener!!)
            else -> FundingHolder(fundBinding, iClickListener!!)
        }
    }
    override fun getItemCount(): Int {
        return when(fragmentType){
            Strings.funding-> customList?.size ?:0
            Strings.license-> customList?.size ?:0
            Strings.house-> 0
            else -> customList?.size ?:0
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
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
            else -> typeFunding
        }
    }
}