package com.universeindustry.governmentinfo.views.fragments.bank.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universeindustry.governmentinfo.databinding.DialogBankDepositBinding
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.views.fragments.bank.recyclerview.ChartDeposit.AdapterDeposit


class DialogBankDeposit(context : Context) : Dialog(context){

    private lateinit var binding : DialogBankDepositBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogBankDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()
        setBasicUI()
        setTitle()
        setContentView()
        setDismiss()
    }
    private fun setBasicUI(){
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 백그라운드 설정
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.let {
            it.cornerRadius = 10.0f
            binding.backView.background = it
        }

        // 탭바 ui설정
        val tabBarGradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(Color.parseColor("#65ffffff"),
                        Color.parseColor("#ffffff")
                )
        )
        val tabBarTopLeft = 10.0f
        val tabBarTopRight = 10.0f
        val tabBarBottomRight = 0.0f
        val tabBarBottomLeft = 0.0f
        tabBarGradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    tabBarTopLeft,
                    tabBarTopLeft,
                    tabBarTopRight,
                    tabBarTopRight,
                    tabBarBottomRight,
                    tabBarBottomRight,
                    tabBarBottomLeft,
                    tabBarBottomLeft
            )
            binding.tabbarContainer.background = it
        }

        // contentView 설정
        val childViewTopLeft = 0.0f
        val childViewTopRight = 0.0f
        val childViewBottomRight = 10.0f
        val childViewBottomLeft = 10.0f
        val childViewGradientDrawable = GradientDrawable()
        childViewGradientDrawable.let {
            it.cornerRadii = floatArrayOf(
                    childViewTopLeft,
                    childViewTopLeft,
                    childViewTopRight,
                    childViewTopRight,
                    childViewBottomRight,
                    childViewBottomRight,
                    childViewBottomLeft,
                    childViewBottomLeft
            )
            it.setColor(Color.parseColor("#bb9496"))
            binding.container.background = it
        }
    }

    private var data : BankDespositModelTree? = null
    fun setData(get : BankDespositModelTree?){
        this.data = get
        setAdapterUpdate()
    }

    private fun setTitle(){
        val getTitle = data?.fin_prdt_nm
        binding.title.text = getTitle
    }
    private fun setContentView(){
        setDetailExplain()
        setRecyclerView()
    }

    private fun setDetailExplain(){
        binding.text.text =
                "서버와 데이터 밀림등의 이유로 내용이 틀릴 수 있으니 주의바랍니다.\n\n" +
                "공시 제출월 : ${data?.dcls_month ?: "에러 발생"}\n" +
                "금융회사 코드 : ${data?.fin_co_no ?: "에러 발생"}\n" +
                "금융회사명 : ${data?.kor_co_nm ?: "에러 발생"}\n" +
                "금융상품 코드 : ${data?.fin_prdt_cd ?: "에러 발생"}\n" +
                "가입 방법 : ${data?.join_way ?: "에러 발생"}\n\n" +
                "만기후 이자율 : ${data?.mtrt_int ?: "에러 발생"}\n\n" +
                "우대조건 : ${data?.spcl_cnd ?: "에러 발생"}\n\n" +
                "가입제한 : ${data?.join_deny ?: "에러 발생"}\n" +
                "가입대상 : ${data?.join_member ?: "에러 발생"}\n\n" +
                "기타 유의사항 : ${data?.etc_note ?: "에러 발생"}\n\n" +
                "최고한도 : ${data?.max_limit ?: "에러 발생"}\n" +
                "공시 시작일 : ${data?.dcls_strt_day ?: "에러 발생"}\n" +
                "공시 종료일 : ${data?.dcls_end_day ?: "에러 발생"}\n" +
                "금융회사 제출일 : ${data?.fin_co_subm_day ?: "에러 발생"}"
    }

    private var adapterDeposit : AdapterDeposit? = null
    private fun setAdapterUpdate(){
        if (adapterDeposit == null) adapterDeposit = AdapterDeposit()
        adapterDeposit?.apply {
            setList(data?.optionListItem ?: arrayListOf())
        }
    }
    private fun setRecyclerView(){
        try {
            binding.recyclerview.apply {
                adapter = adapterDeposit
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                setItemViewCacheSize(0)
                setHasFixedSize(true)
            }
        }catch (e:Exception){
            Log.e("mException", "FragmentDeposit, setRecyclerView // Exception : ${e.message}")
        }
    }

    private fun setDismiss(){
        binding.dismiss.let {
            it.setOnClickListener {
                if (this.isShowing){
                    this.dismiss()
                }
            }
        }
    }
}