package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log.e
import com.google.gson.JsonElement
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import com.universeindustry.governmentinfo.online.retrofit.model.OptionListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallingManager {

    companion object {
        val instance = RetrofitCallingManager()
    }

    private val iRetrofit : (String, String) -> IRetrofit? = { url, type -> RetrofitCreater.getClient(url, type)?.create(IRetrofit::class.java) }
    fun getBankListData(completion: (ArrayList<BankDespositModelTree>) -> Unit){
        val call = iRetrofit(API.bankingBaseURL, "JSON")?.getBankingDespositByPapge(searchBank = "1") ?: return
        call.enqueue(object : Callback<JsonElement>{
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                e("mException", "에러발생 : RetrofitCallingManager // Throwable : $t")
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful && response.code() == 200){
                    response.body().let {
                        val body = it?.asJsonObject?.getAsJsonObject("result")
                        when(body?.get("err_msg")?.asString){
                            "정상" -> {

                                val despositList : ArrayList<BankDespositModelTree> = arrayListOf()
                                body.getAsJsonArray("baseList").forEach {
                                    val data =  it.asJsonObject
                                    val dcls_month = data.get("dcls_month").asString // 공시 제출월
                                    val fin_co_no = data.get("fin_co_no").asString // 금융회사 코드
                                    val fin_prdt_cd = data.get("fin_prdt_cd").asString // 상품이름
                                    val kor_co_nm = data.get("kor_co_nm").asString // 금융상품 코드
                                    val fin_prdt_nm = data.get("fin_prdt_nm").asString // 금융 상품명
                                    val join_way = data.get("join_way").asString // 가입 방법
                                    val mtrt_int = data.get("mtrt_int").asString // 만기후 이자율
                                    val spcl_cnd = data.get("spcl_cnd").asString // 우대조건
                                    val join_deny = data.get("join_deny").asString // 가입제한
                                    val join_member = data.get("join_member").asString // 가입대상
                                    val etc_note = data.get("etc_note").asString // 기타 유의사항
                                    val max_limit = data.get("max_limit").toString() // 최고한도
                                    val dcls_strt_day = data.get("dcls_strt_day").asString // 공시 시작일
                                    val dcls_end_day = data.get("dcls_end_day").toString() // 공시 종료일
                                    val fin_co_subm_day = data.get("fin_co_subm_day").asString // 금융회사 제출일

                                    val despositItem = BankDespositModelTree(
                                            dcls_month = dcls_month,
                                            fin_co_no = fin_co_no,
                                            kor_co_nm = kor_co_nm,
                                            fin_prdt_cd = fin_prdt_cd,
                                            fin_prdt_nm = fin_prdt_nm,
                                            join_way = join_way,
                                            mtrt_int = mtrt_int,
                                            spcl_cnd = spcl_cnd,
                                            join_deny = join_deny,
                                            join_member = join_member,
                                            etc_note = etc_note,
                                            max_limit = max_limit,
                                            dcls_strt_day = dcls_strt_day,
                                            dcls_end_day = dcls_end_day,
                                            fin_co_subm_day = fin_co_subm_day,
                                            optionListItem = arrayListOf()
                                    )
                                    despositList.add(despositItem)
                                }

                                val optionList = body.getAsJsonArray("optionList")
                                optionList.forEachIndexed { index, jsonElement ->
                                    val data =  jsonElement.asJsonObject
                                    val fin_prdt_cd = data.get("fin_prdt_cd").asString // 금융상품 코드
                                    val intr_rate_type = data.get("intr_rate_type").asString // 저축 금리 유형
                                    val intr_rate_type_nm = data.get("intr_rate_type_nm").asString // 저축 금리 유형명
                                    val save_trm = data.get("save_trm").asString // 저축 기간 (단위 : 개월)
                                    val intr_rate = data.get("intr_rate").asString // 저축 금리 (소수점 2자리)
                                    val intr_rate2 = data.get("intr_rate2").asString // 최고 우대금리 (소수점 2자리)

                                    for (i in despositList.indices){
                                        if (despositList[i].fin_prdt_cd == fin_prdt_cd){
                                            val optionListItem = OptionListItem(
                                                    intr_rate_type = intr_rate_type,
                                                    intr_rate_type_nm = intr_rate_type_nm,
                                                    save_trm = save_trm,
                                                    intr_rate = intr_rate,
                                                    intr_rate2 = intr_rate2
                                            )
                                            despositList[i].optionListItem.add(optionListItem)
                                        }
                                    }
                                }
                                completion(despositList)
                            }
                            else -> {
                                // Open API 서비스 내부 시스템 에러

                            }
                        }

//                        it?.asJsonObject
//                        JSONObject(message).toString(4)
//                        completion(JSONObject(it.toString()).toString(4))
                    }
                }
            }
        })
    }
}

/*

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitCreater.getClient(API.licenseListURLTest)?.create(IRetrofit::class.java)
    fun getLicenseList(completion : (String) -> Unit){
        val call = iRetrofit?.getLicenseTItles(query = API.licenseListAuthKey) ?: return
        call.enqueue(object : retrofit2.Callback<Items>{
            override fun onFailure(call: Call<Items>, t: Throwable) {
                e("mException", "에러발생 -> RetrofitCallingManager, getLicenseList, onFailure() called // Throwable : $t")
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                e("mException", "응답성공 -> RetrofitCallingManager, getLicenseList, onResponse() called // response : ${response.body()}")
                if (response.isSuccessful){
                    when(response.code()){
                        // 응답성공시
                        200 -> {
                            response.body()?.let {
                                val body = it
//                            val results = body.getAsJsonArray("results")
//                            val total = body.get("total").asInt
//                            e("mException", "RetrofitManager - onResponse() called / total: $total")
                                e("mException", " / body: $body")

                                // 데이터가 없으면 no_content 로 보낸다.
//                            if(total == 0) { completion("비어있는 데이터")
//                            } else { // 데이터가 있다면
//
//                                results.forEach { resultItem ->
//                                    val resultItemObject = resultItem.asJsonObject
//                                    val user = resultItemObject.get("user").asJsonObject
//                                    val username : String = user.get("username").asString
//                                    val likesCount = resultItemObject.get("likes").asInt
//                                    val thumbnailLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString
//                                    val createdAt = resultItemObject.get("created_at").asString
//                                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//                                    val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")
//
//                                    val outputDateString = formatter.format(parser.parse(createdAt))
//
//                                    //                                Log.d(TAG, "RetrofitManager - outputDateString : $outputDateString")
//
//                                    val photoItem = LicenseModel(
//                                            title = username,
//                                            examFee = likesCount,
//                                            writeenTestDate = thumbnailLink,
//                                            practicalTestDate = outputDateString
//                                    )
//
//                                    val licenseTitleList = ArrayList<LicenseModel>()
//                                    licenseTitleList.add(photoItem)
//                                }
//
//                                completion(RESPONSE_STATUS.OKAY , parsedPhotoDataArray)
//                            }
                            }
                        }
                    }
                }else{
                    e("mException", "응답실패 -> RetrofitCallingManager, getLicenseList, onResponse() called, responese.NotSuccessful ")

                }
            }
        })
    }
 */
