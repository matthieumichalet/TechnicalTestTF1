package io.involvedapps.domain.usecases

import io.involvedapps.domain.models.ArticleEntity
import io.involvedapps.domain.repositories.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesUseCases(
    private val repository: ArticlesRepository
) {

    suspend fun getArticles(): List<ArticleEntity> {
        return withContext(Dispatchers.IO) {
            val articles = repository.getArticles()

            repository.saveArticles(articles)
            articles
        }
    }

    suspend fun getArticleDetail(id: String): ArticleEntity {
        return withContext(Dispatchers.IO) {
            val article = repository.getArticleDetail(id)

            repository.saveArticle(article)
            article
        }
    }

}