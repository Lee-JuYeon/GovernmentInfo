package com.universeindustry.governmentinfo.online.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofit {
    /*
                                [   GET    ]     [@Query]="${query}"
     https://www.unsplash.com/  search/photo  /  ?query  ="        "
     */

    // 테스트 쿼리입니다.
    @GET("{query}")
    fun mTextQuery(@Query("query") query : String) : Call<JsonElement>

}

/*

        어노테이션	설명
        @Path	API 엔드포인트의 변수를 대채
        @Query	어노테이션의 매개변수 값으로 쿼리 키 이름을 지정
        @Body	Post 호출의 페이로드
        @Header	어노테이션의 매개변수 값으로 헤더를 지정
        @GET    해당 메소드가 호출 되면 실행된 @GET Request를 명시적으로 정의한다.

        HTTP method
        POST   = 리소스 생성
        GET    = 리소스 조회 및 획득
        PUT    = 리소스 수정
        DELETE = 리소스 삭제

        // 사이트 주소
        @field:Element(name = "rqutUrla")
        @param:Element(name = "rqutUrla")
        val siteURL: String? = null,

        // 신청기간
        @field:Element(name = "rqutPrdCn")
        @param:Element(name = "rqutPrdCn")
        val period: String? = null,

        // 지원내용
        @field:Element(name = "sporCn")
        @param:Element(name = "sporCn")
        val supportContents : String? = null,

        // 참여조건 : 연령대
        @field:Element(name = "ageInfo")
        @param:Element(name = "ageInfo")
        val conditions_Age : String? = null,

        // 정책명
        @field:Element(name = "polyBizSjnm")
        @param:Element(name = "polyBizSjnm")
        val title : String? = null

        http://openapi.work.go.kr/opi/opi/opia/jynEmpSptListAPI.do
        http://openapi.work.go.kr/opi/opi/opia/jynEmpSptListAPI.do?
        authKey=[인증키] &
        returnType=xml &
        busiTpcd=PLCYTP01 &
        chargerClcd=G &
        startPage=1 &
        display=20

        https://www.youthcenter.go.kr/opi/empList.do ?
        query="" &
        display=100 &
        pageIndex=1 &
        srchPolicyId=""
 */