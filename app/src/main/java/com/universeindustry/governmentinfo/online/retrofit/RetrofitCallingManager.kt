package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log
import android.util.Log.e
import com.google.gson.JsonElement
import com.universeindustry.governmentinfo.online.retrofit.models.LicenseBodyModel
import com.universeindustry.governmentinfo.views.fragments.license.LicenseModel
import retrofit2.Call
import retrofit2.Response

class RetrofitCallingManager {

    companion object {
        val instance = RetrofitCallingManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitCreater.getClient(API.licenseListURLTest)?.create(IRetrofit::class.java)


    // 예제 쿼리입니다.
    fun testQuery(searchTerm: String?, completion: (String) -> Unit){
        val call = iRetrofit?.mTextQuery(query = searchTerm ?: "") ?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e("mException", "에러발생 -> RetrofitManager, call, onFailure() called / t: $t")
                completion( t.toString())
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.e("mException", "응답성공 -> RetrofitManager, call, onResponse() called / response : ${response.body()}")
                completion(response.raw().toString())
            }
        })
    }

    fun getLicenseList(completion : (String) -> Unit){
        val call = iRetrofit?.getLicenseTItles(query = API.licenseListAuthKey) ?: return
        call.enqueue(object : retrofit2.Callback<LicenseBodyModel>{
            override fun onFailure(call: Call<LicenseBodyModel>, t: Throwable) {
                e("mException", "에러발생 -> RetrofitCallingManager, getLicenseList, onFailure() called // Throwable : $t")
            }
            override fun onResponse(call: Call<LicenseBodyModel>, response: Response<LicenseBodyModel>) {
                e("mException", "응답성공 -> RetrofitCallingManager, getLicenseList, onResponse() called // response : ${response.body()}")
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
            }
        })
    }
}