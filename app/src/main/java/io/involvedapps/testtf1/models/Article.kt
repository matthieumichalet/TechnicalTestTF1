package io.involvedapps.testtf1.models

import io.involvedapps.domain.models.ArticleEntity

class Article(
    val id: String = "",
    val title: String,
    val webUrl: String,
    val webPublicationDate: String,
    val imageUrl: String,
    val content: String = "" //optionnal, only if the user click on article to get details
) {

    companion object Mapper {

        fun mapFromEntities(articles: List<ArticleEntity>): List<Article> {
            val result = mutableListOf<Article>()
            for (article in articles) {
                result.add(mapFromEntity(article))
            }
            return result
        }

        fun mapFromEntity(article: ArticleEntity): Article {
            return Article(
                id = article.id,
                title = article.title,
                webUrl = article.webUrl,
                webPublicationDate = article.webPublicationDate,
                imageUrl = article.imageUrl,
                content = article.content
            )
        }

    }

}