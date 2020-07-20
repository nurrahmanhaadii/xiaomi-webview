package id.haadii.visionplus.xiaomiwebview.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

/**
 * Created by nurrahmanhaadii on 15,July,2020
 */
class ApiServiceGenerator(val baseUrl: String) {

    private val httpClient = OkHttpClient.Builder()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://partners-api.visionplus.id/api/v1/partnership/xiaomi/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun <S> createService(serviceClass: Class<S>?): S {
        val client: OkHttpClient = httpClient
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        val retrofit: Retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }


    fun <S> createService(serviceClass: Class<S>?, lang: String?): S {
        return createService(serviceClass, null, lang)
    }

    fun <S> createService(
        serviceClass: Class<S>?,
        clientId: String?,
        secretKey: String?
    ): S {
        if (clientId != null) {
            httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("X-CLIENT-ID", clientId)
                    .header("X-SECRET-KEY", secretKey!!)
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            })
        }
        val client: OkHttpClient = httpClient
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }
}