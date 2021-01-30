package com.universeindustry.governmentinfo.online.retrofit.model

import java.io.Serializable

data class BankDespositModelTree(
        val dcls_month : String?,           // 공시 제출월
        val fin_co_no : String?,            // 금융회사 코드
        val kor_co_nm : String?,            // 금융회사명
        val fin_prdt_cd : String?,          // 금융상품 코드
        val fin_prdt_nm : String?,          // 금융 상품명
        val join_way : String?,             // 가입 방법
        val mtrt_int : String?,             // 만기후 이자율
        val spcl_cnd : String?,             // 우대조건
        val join_deny : String?,            // 가입제한
        val join_member : String?,          // 가입대상
        val etc_note : String?,             // 기타 유의사항
        val max_limit : String?,            // 최고한도
        val dcls_strt_day : String?,        // 공시 시작일
        val dcls_end_day : String?,         // 공시 종료일
        val fin_co_subm_day : String?,      // 금융회사 제출일
        var optionListItem : ArrayList<OptionListItem?> // 옵션 리스트
) : Serializable {
    override fun toString(): String {
        return "\n" +
                "공시 체출월 : $dcls_month \n" +
                "금융회사 코드 : $fin_co_no \n" +
                "금융회사명 : $kor_co_nm \n" +
                "금융상품 코드 : $fin_prdt_cd \n" +
                "금융 상품명 : $fin_prdt_nm \n" +
                "가입 방법 : $join_way \n" +
                "만기후 이자율 : $mtrt_int \n" +
                "우대조건 : $spcl_cnd \n" +
                "가입제한 : $join_deny \n" +
                "가입대상 : $join_member \n" +
                "기타 유의사항 : $etc_note \n" +
                "최고한도 : $max_limit \n" +
                "공시 시작일 : $dcls_strt_day \n" +
                "공시 종료일 : $dcls_end_day \n" +
                "금융회사 제출일 : $fin_co_subm_day \n" +
                "optionList : $optionListItem \n" +
                ""
    }
}
data class OptionListItem(
        var intr_rate_type : String?,       // 저축 금리 유형
        var intr_rate_type_nm : String?,    // 저축 금리 유형명
        var save_trm : String?,             // 저축 기간 (단위 : 개월)
        var intr_rate : String?,            // 저축 금리 (소수점 2자리)
        var intr_rate2 : String?            // 최고 우대금리 (소수점 2자리)
): Serializable {
    override fun toString(): String {
        return  "\n" +
                "  저축 금리 유형 : $intr_rate_type \n" +
                "  저축 금리 유형명 : $intr_rate_type_nm \n" +
                "  저축 기간 (단위 : 개월) : $save_trm \n" +
                "  저축 금리 (소수점 2자리) : $intr_rate \n" +
                "  최고 우대금리 (소수점 2자리) : $intr_rate2"
    }
}



