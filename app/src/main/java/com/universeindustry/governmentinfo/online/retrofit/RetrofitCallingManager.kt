package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log
import com.google.gson.JsonElement
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

    fun setQuery(query : String, completion : (String) -> Unit){
        val call = iRetrofit?.getLicenseTItles(
//                query = API.licenseListAuthKey
                query = query
        ) ?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e("mException", "에러발생 -> RetrofitCallingManager, setQuery, onFailure() called // t: $t")
                completion( t.toString())
            }
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.e("mException", "응답성공 -> RetrofitCallingManager, setQuery, onResponse() called // response : ${response.body()}")
                completion(response.raw().toString())
            }
        })
    }
}