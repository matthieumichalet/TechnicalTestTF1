package io.involvedapps.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.involvedapps.domain.models.ArticleEntity

@Entity(tableName = "article")
data class ArticleData(
    @PrimaryKey val id: String = "",
    val title: String,
    val webUrl: String,
    val webPublicationDate: String,
    val imageUrl: String,
    val content: String = "" //optionnal, only if the user click on article to get details
) {

    companion object Mapper {

        fun mapToEntities(articles: List<ArticleData>): List<ArticleEntity> {
            val result = mutableListOf<ArticleEntity>()
            for (article in articles) {
                result.add(mapToEntity(article))
            }
            return result
        }

        fun mapToEntity(article: ArticleData): ArticleEntity {
            return ArticleEntity(
                id = article.id,
                title = article.title,
                webUrl = article.webUrl,
                webPublicationDate = article.webPublicationDate,
                imageUrl = article.imageUrl,
                content = article.content
            )
        }

        fun mapFromEntities(articles: List<ArticleEntity>): List<ArticleData> {
            val result = mutableListOf<ArticleData>()
            for (article in articles) {
                result.add(mapFromEntity(article))
            }
            return result
        }

        fun mapFromEntity(article: ArticleEntity): ArticleData {
            return ArticleData(
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