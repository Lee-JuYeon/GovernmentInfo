package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log.e
import com.universeindustry.governmentinfo.utils.extensions.Strings.isJsonArray
import com.universeindustry.governmentinfo.utils.extensions.Strings.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitCreater {
    // retrofit 클라이언트 선언
    private var retrofitClient : Retrofit? = null


    // retrofit 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit?{
        try {
            setOkHttpBuilder()
            if (retrofitClient == null){
                // retrofit 빌더를 통해 인스턴스 생성
                retrofitClient = Retrofit.Builder()
                        .baseUrl(baseUrl)
//                    .client(OkHttpClient().newBuilder().build())
//                    .addConverterFactory(Simple)
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build())
                        .build()
            }
        }catch (e:Exception){
            e("mException", "RetrofitClient, getClient // Exception : ${e.message}")
        }finally {
            return retrofitClient
        }
    }


    // okhttp 인스턴스 생성
    private val client = OkHttpClient.Builder()
    private fun setOkHttpBuilder(){
        setLoggingInterecepter()
        setBaseParameterIntercepter()

        // 커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)
    }

    private fun setLoggingInterecepter(){
        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{

            override fun log(message: String) {
//                Log.d(TAG, "RetrofitClient - log() called / message: $message")

                when {
                    message.isJsonObject() -> e("mException", JSONObject(message).toString(4))
                    message.isJsonArray() -> e("mException", JSONObject(message).toString(4))
                    else -> {
                        try {
                            e("mException", JSONObject(message).toString(4))
                        } catch (e: Exception) {
                            e("mException", message)
                        }
                    }
                }

            }

        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)
    }
    private fun setBaseParameterIntercepter(){
        // 기본 파라매터 인터셉터 설정
        val baseParameterInterceptor : Interceptor = (object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                e("mException", "RetrofitClient - intercept() called")
                // 오리지날 리퀘스트
                val originalRequest = chain.request()

                // ?client_id=asdfadsf
                // 쿼리 파라매터 추가하기
                val addedUrl = originalRequest
                    .url
                    .newBuilder()
//                            .addQueryParameter("client_id", API.CLIENT_ID)
                    .build()
                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()
                return chain.proceed(finalRequest)
            }
        })
        // 위에서 설정한 기본파라매터 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(baseParameterInterceptor)
    }
}