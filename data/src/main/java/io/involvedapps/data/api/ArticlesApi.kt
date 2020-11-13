package io.involvedapps.data.api

import io.involvedapps.data.models.ArticleData
import retrofit2.http.GET
import retrofit2.http.Path


interface ArticlesApi {

    @GET("search?show-fields=thumbnail&page-size=50&q=football%20and%20brexit")
    suspend fun getArticles(): List<ArticleData>

    @GET("{id}?show-fields=main%2Cbody%2Cthumbnail")
    suspend fun getArticleDetail(@Path(value = "id", encoded = true) articleId: String): ArticleData

}