package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)


    // 예제 쿼리입니다.
    fun testQuery(searchTerm: String?, completion: (String) -> Unit){
        val call = iRetrofit?.mTextQuery(query = searchTerm ?: "") ?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e("mException", "RetrofitManager, call, onFailure() called / t: $t")
                completion( t.toString())
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.e("mException", "RetrofitManager, call, onResponse() called / response : ${response.body()}")
                completion(response.raw().toString())
            }
        })
    }
}