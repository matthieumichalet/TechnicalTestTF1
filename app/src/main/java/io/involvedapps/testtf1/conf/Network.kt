package io.involvedapps.testtf1.conf

import com.google.gson.GsonBuilder
import io.involvedapps.data.api.GetArticleDeserializer
import io.involvedapps.data.api.GetArticlesDeserializer
import io.involvedapps.data.models.ArticleData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createNetworkClient(baseUrl: String): Retrofit {
    return retrofitClient(
        baseUrl,
        httpClient()
    )
}

private fun httpClient(): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.addInterceptor(Interceptor { chain ->
        val builder = chain.request().newBuilder()
        builder.header("api-key", "b910f9e7-183e-4041-893c-76456b317c44")
        return@Interceptor chain.proceed(builder.build())
    })
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder()
        .registerTypeAdapter(List::class.java, GetArticlesDeserializer())
        .registerTypeAdapter(ArticleData::class.java, GetArticleDeserializer())
        .create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}