package io.involvedapps.data.repositories

import io.involvedapps.data.api.ArticlesApi
import io.involvedapps.data.models.ArticleData

class ArticlesRemoteService(
    private val api: ArticlesApi
) {

    suspend fun getArticles(): List<ArticleData> {
        return api.getArticles()
    }

    suspend fun getArticleDetail(id: String): ArticleData {
        return api.getArticleDetail(id)
    }
}