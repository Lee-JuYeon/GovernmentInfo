package com.universeindustry.governmentinfo.online.retrofit

import com.universeindustry.governmentinfo.utils.datatype.TypeConverting.toDecodedURL
import com.universeindustry.governmentinfo.utils.datatype.TypeConverting.toURL

object API{
    // retrofit2 타입
    const val XML = "XML"
    const val JSON = "JSON"

    //--------------------- 온라인 청년 센터 api-----------------------------------//
    const val BASE_URL = "https://www.youthcenter.go.kr/opi/empList.do/"
    // 테스트용 쿼리입니다. 제가 봤을 때는 쿼리 문제인 것 같은데, 제 딴에는 해당 쿼리가 옳은 것 같습니다.
    const val mQuery = "pageIndex=10&display=10&bizTycdSel=004001,004002003"

    //--------------------- 국가자격증 리스트 api-----------------------------------//
    val licenseListAuthKey = "0M7LP9tjMbQFDvl0pXyQGtQXku4zclBZV0nhZiMR9j7oMdSEWZR2ubH7eBo8dy0dPYWeQ9ustmtuWf5oK79ybg%3D%3D"
//                    .toDecodedURL()
//            .toURL()
//    const val licenseListURL = "http://openapi.q-net.or.kr/api/service/rest/InquiryListNationalQualifcationSVC/"
    const val licenseListURLTest = "http://testapi.q-net.or.kr/api/service/rest/InquiryListNationalQualifcationSVC/"

    /*
     예*적금 금리 리스트 API
    http://finlife.fss.or.kr/finlifeapi/
    depositProductsSearch.json ?
    auth=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx &
    topFinGrpNo=020000 &
    pageNo=1
     */
    const val bankingAuthKey = "56083c1c015436cde4007260668ef487"
    const val bankingBaseURL = "http://finlife.fss.or.kr/finlifeapi/"

    // 정기예금
    const val bankingDesposit = "depositProductsSearch.json?auth=56083c1c015436cde4007260668ef487&topFinGrpNo=020000"
    // 적금
    val bankingSaving = "savingProductsSearch.json"
    // 연금저축
    val bankingAnnuitySaving = "annuitySavingProductsSearch.json"
}