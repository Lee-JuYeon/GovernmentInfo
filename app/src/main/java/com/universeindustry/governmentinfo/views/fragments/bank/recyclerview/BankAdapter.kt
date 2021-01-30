package com.universeindustry.governmentinfo.views.fragments.bank.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.databinding.HolderBankDespositBinding
import com.universeindustry.governmentinfo.online.retrofit.API
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.recyclerview.IClickListener
import com.universeindustry.governmentinfo.views.base.BaseDiffUtil


class BankAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var customList : ArrayList<Any>? = arrayListOf()
    private var fragmentType : String? = null
    fun setRecyclerviewType(get : String, newList : List<Any>){
        // 타입 업데이트
        this.fragmentType = get

        // 리스트 업데이트
        val diffResult = DiffUtil.calculateDiff(BaseDiffUtil(
                customList ?: arrayListOf(),
                newList,
                get), false)
        diffResult.dispatchUpdatesTo(this@BankAdapter)
        customList?.clear()
        customList?.addAll(newList)
    }

    private var iClickListener : IClickListener? = null
    fun setClickListener(get : IClickListener?){ this.iClickListener = get }

    private val typeDesposit : Int = 0
    private val typeSaving : Int = 1
    private val typeAnnuitySaving : Int = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bankDespositBinding = HolderBankDespositBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            typeDesposit -> DespositHolder(bankDespositBinding, iClickListener!!)
            typeSaving -> DespositHolder(bankDespositBinding, iClickListener!!)
            typeAnnuitySaving -> DespositHolder(bankDespositBinding, iClickListener!!)
            else -> DespositHolder(bankDespositBinding, iClickListener!!)
        }
    }

    override fun getItemCount(): Int {
        return when(fragmentType){
            API.desposit -> customList?.size ?: 0
            API.saving -> customList?.size ?: 0
            API.annuitySaving -> customList?.size ?: 0
            else -> customList?.size ?: 0
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DespositHolder -> {
                val dataList = customList!![position]
                holder.dataBinding(
                        model = dataList as BankDespositModelTree
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("BankAdapter, onBindViewHolder // Exception : ${exception.message}")
            }
        }
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when(holder){
            is DespositHolder -> {
                holder.dataBinding(
                        model = null
                )
            }
            else -> {
                val exception = Exception()
                throw Exception("BankAdapter, onViewRecycled // Exception : ${exception.message}")
            }
        }
        super.onViewRecycled(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return when(fragmentType){
            API.desposit -> typeDesposit
            API.saving -> typeSaving
            API.annuitySaving -> typeAnnuitySaving
            else -> typeDesposit
        }
    }
}