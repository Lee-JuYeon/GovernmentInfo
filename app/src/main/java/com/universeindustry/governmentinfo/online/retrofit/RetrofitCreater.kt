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

    // okhttp 인스턴스 생성
    private val client = OkHttpClient.Builder()
    private fun setLoggingIntercepter(){
        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                try {
                    when {
                        message.isJsonObject() ->
                            e("mException", "isJsonObject : ${JSONObject(message).toString(4)}")
                        message.isJsonArray() ->
                            e("mException", "isJsonArray : ${JSONObject(message).toString(4)}")
                        else -> {
                            try {
                                e("mException", "log : ${message}")
                            } catch (e: Exception) {
                                e("mException", "에러발생 -> RetrofitCreater, setLoggingIntercepter, loggingInterceptor //  Exception : ${message}")
                            }
                        }
                    }
                } catch (e: Exception) {
                    e("mException", "에러발생 -> RetrofitCreater, setLoggingIntercepter // Exception : ${e.message}")
                }
            }
        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)
    }
    private fun setOkHttpBuilder(){
        setLoggingIntercepter()
//        setParameterIntercepter()

        // 커넥션 타임아웃
        client.apply {
            connectTimeout(328, TimeUnit.SECONDS)
            readTimeout(328, TimeUnit.SECONDS)
            writeTimeout(328, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }
    }

    // retrofit 클라이언트 가져오기
    fun getClient(baseUrl: String, type : String): Retrofit?{
        try {
            setOkHttpBuilder()
            if (retrofitClient == null){
                // retrofit 빌더를 통해 인스턴스 생성
                when(type){
                    API.JSON -> {
                        // 레트로핏 빌더를 통해 인스턴스 생성
                        retrofitClient = Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())

                                // 위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                                .client(client.build())

                                .build()
                    }
                    API.XML -> {
                        retrofitClient = Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(SimpleXmlConverterFactory.create())
//                        .addConverterFactory(
//                                TikXmlConverterFactory.create(
//                                        TikXml.Builder()
//                                                .exceptionOnUnreadXml(false)
//                                                .addTypeConverter(String.javaClass, HtmlEscapeStringConverter())
//                                                .build()
//                                )
//                        )
                                .client(client.build())
                                .build()
                    }
                }
            }
        }catch (e:Exception){
            e("mException", "RetrofitClient, getClient // Exception : ${e.message}")
        }finally {
            return retrofitClient
        }
    }
}
/*
private fun setParameterIntercepter(){
        // 기본 파라매터 인터셉터 설정
        val baseParameterInterceptor : Interceptor = (object : Interceptor{

            override fun intercept(chain: Interceptor.Chain): Response {
                // 오리지날 리퀘스트
                val originalRequest = chain.request()

                // ?client_id=asdfadsf
                // 쿼리 파라매터 추가하기
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("auth", API.bankingAuthKey).build()

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
 */