package io.involvedapps.domain.models

data class ArticleEntity(
    val id: String = "",
    val title: String,
    val webUrl: String,
    val webPublicationDate: String,
    val imageUrl: String,
    val content: String = "" //optionnal, only if the user click on article to get details
)