package io.involvedapps.data.api

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.involvedapps.data.models.ArticleData
import java.lang.Exception
import java.lang.reflect.Type

class GetArticleDeserializer: JsonDeserializer<ArticleData?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ArticleData? {
        json?.let {
            val jsonArticle = it.asJsonObject.getAsJsonObject("response")
                .getAsJsonObject("content")

            return try {
                val id = jsonArticle.get("id").asString
                val title = jsonArticle.get("webTitle").asString
                val webUrl = jsonArticle.get("webUrl").asString
                val webPublicationDate = jsonArticle.get("webPublicationDate").asString
                val jsonFields = jsonArticle.getAsJsonObject("fields")
                val imageUrl = jsonFields.get("thumbnail").asString
                val body = jsonFields.get("body").asString

                ArticleData(
                    id = id,
                    title = title,
                    webUrl = webUrl,
                    webPublicationDate = webPublicationDate,
                    imageUrl = imageUrl,
                    content = body
                )
            } catch (exception: Exception) {
                Log.v(TAG, exception.toString())
                null
            }
        }
        return null
    }

    companion object {

        private const val TAG = "GetArticleDeserializer"

    }

}