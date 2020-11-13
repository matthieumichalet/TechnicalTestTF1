package io.involvedapps.domain.repositories

import io.involvedapps.domain.models.ArticleEntity

interface ArticlesRepository {
    suspend fun getArticles(): List<ArticleEntity>
    suspend fun getArticleDetail(id: String): ArticleEntity
    suspend fun saveArticles(articles: List<ArticleEntity>)
    suspend fun saveArticle(article: ArticleEntity)
}