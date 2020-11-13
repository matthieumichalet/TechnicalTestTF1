package io.involvedapps.data.repositories

import io.involvedapps.data.db.AppDatabase
import io.involvedapps.data.models.ArticleData

class ArticlesLocalService(
    database: AppDatabase
) {

    private val dao = database.getArticlesDao()

    suspend fun getArticles(): List<ArticleData> {
        return dao.getArticles()
    }

    suspend fun getArticle(id: String): ArticleData {
        return dao.getArticle(id)
    }

    suspend fun saveArticles(articles: List<ArticleData>) {
        dao.saveArticles(articles = articles)
    }

    suspend fun saveArticle(article: ArticleData) {
        dao.saveArticle(article = article)
    }

    suspend fun clear() {
        dao.clear()
    }

}