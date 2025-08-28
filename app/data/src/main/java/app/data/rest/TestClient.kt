package app.data.rest

import app.common.network.HttpLoggingInterceptor
import app.common.network.ResultCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration


class TestClient (val restConfig: RestConfig){

    private inner class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response =
            chain.request().newBuilder().build().let {
                val z = chain.proceed(it)
                println(z)
                z
            }

    }

    private val retrofit by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(Duration.ofSeconds(restConfig.readTimeoutInSeconds.toLong()))
            .writeTimeout(Duration.ofSeconds(restConfig.writeTimeoutInSeconds.toLong()))
            .build().let {
                Retrofit.Builder().client(it).baseUrl(restConfig.baseUrl).addConverterFactory(
                    GsonConverterFactory.create()
                )
                    .addCallAdapterFactory(ResultCallAdapterFactory())
                    .build()
            }
    }
    fun <T> create(claz:Class<T>) = retrofit.create(claz)
}