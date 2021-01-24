package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log.e
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.universeindustry.governmentinfo.online.retrofit.model.BankDespositModelTree
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallingManager {

    companion object {
        val instance = RetrofitCallingManager()
    }

    private val iRetrofit : (String, String) -> IRetrofit? = { url, type -> RetrofitCreater.getClient(url, type)?.create(IRetrofit::class.java) }
    fun getBankListData(completion: (String) -> Unit){
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
//                        it?.asJsonObject
//                        JSONObject(message).toString(4)
                        completion(JSONObject(it.toString()).toString(4))
                    }
                }
//                response.body()?.let {
//                    var parsedDespositDataArray = ArrayList<BankDespositModelTree>()
//
//                    val body = it.asJsonObject
//                    val baseList = body.asJsonArray
//                    e("mException", "body // $body")
//
//                    val totalCount = body.get("total_count")
//                }
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
