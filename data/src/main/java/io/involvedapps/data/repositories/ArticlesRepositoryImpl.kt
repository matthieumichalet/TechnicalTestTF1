package io.involvedapps.data.repositories

import android.util.Log
import io.involvedapps.data.models.ArticleData
import io.involvedapps.domain.models.ArticleEntity
import io.involvedapps.domain.repositories.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ArticlesRepositoryImpl(
    private val remoteService: ArticlesRemoteService,
    private val localService: ArticlesLocalService
): ArticlesRepository {

    override suspend fun getArticles(): List<ArticleEntity> {
        return withContext(Dispatchers.IO) {
            val retrievedArticles = try {
                remoteService.getArticles()
            } catch (exception: Exception) {
                Log.v(TAG, exception.toString())
                localService.getArticles()
            }
            sortArticlesByDate(ArticleData.mapToEntities(retrievedArticles))
        }
    }

    private fun sortArticlesByDate(articles: List<ArticleEntity>): List<ArticleEntity> {
        return articles.sortedByDescending { it.webPublicationDate }
    }

    override suspend fun getArticleDetail(id: String): ArticleEntity {
        return withContext(Dispatchers.IO) {
            val article: ArticleData = try {
                remoteService.getArticleDetail(id)
            } catch (exception: Exception) {
                Log.v(TAG, exception.toString())
                localService.getArticle(id)
            }
            ArticleData.mapToEntity(article)
        }
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        localService.saveArticles(ArticleData.mapFromEntities(articles))
    }

    override suspend fun saveArticle(article: ArticleEntity) {
        localService.saveArticle(ArticleData.mapFromEntity(article))
    }

    companion object {

        private const val TAG = "ArticlesRepositoryImpl"

    }

}