package io.involvedapps.data.api

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.involvedapps.data.models.ArticleData
import java.lang.Exception
import java.lang.reflect.Type

class GetArticlesDeserializer: JsonDeserializer<List<ArticleData>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ArticleData> {
        val result = mutableListOf<ArticleData>()
        json?.let {
            val jsonArticles = it.asJsonObject.getAsJsonObject("response")
                .getAsJsonArray("results")

            for (jsonElement in jsonArticles) {
                try {
                    val jsonArticle = jsonElement.asJsonObject
                    val id = jsonArticle.get("id").asString
                    val title = jsonArticle.get("webTitle").asString
                    val webUrl = jsonArticle.get("webUrl").asString
                    val webPublicationDate = jsonArticle.get("webPublicationDate").asString
                    val imageUrl = jsonArticle.getAsJsonObject("fields").get("thumbnail").asString

                    result.add(
                        ArticleData(
                            id = id,
                            title = title,
                            webUrl = webUrl,
                            webPublicationDate = webPublicationDate,
                            imageUrl = imageUrl
                        )
                    )
                } catch (exception: Exception) {
                    Log.v(TAG, exception.toString())
                }
            }
        }
        return result
    }

    companion object {

        private const val TAG = "GetArticlesDeserializer"

    }

}