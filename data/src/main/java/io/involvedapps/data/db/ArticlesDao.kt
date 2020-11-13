package io.involvedapps.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.involvedapps.data.models.ArticleData

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article")
    suspend fun getArticles(): List<ArticleData>

    @Query("SELECT * FROM article WHERE id LIKE :id")
    suspend fun getArticle(id: String): ArticleData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticles(articles: List<ArticleData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: ArticleData)

    @Query("DELETE FROM article")
    suspend fun clear()

}