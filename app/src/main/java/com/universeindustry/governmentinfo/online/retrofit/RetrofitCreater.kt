package com.universeindustry.governmentinfo.online.retrofit

import android.util.Log.e
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
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
//                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .addConverterFactory(
                                TikXmlConverterFactory.create(
                                        TikXml.Builder()
                                                .exceptionOnUnreadXml(false)
                                                .addTypeConverter(String.javaClass, HtmlEscapeStringConverter())
                                                .build()
                                )
                        )
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
        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                try {
                    e("mException", "log : ${message}")
                } catch (e: Exception) {
                    e("mException", "에러발생 -> RetrofitCreater, setLoggingInterecepter // Exception : ${e.message}")
                }
            }
        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // 커넥션 타임아웃
        client.apply {
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            writeTimeout(120, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }
    }
}