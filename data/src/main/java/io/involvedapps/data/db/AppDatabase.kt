package io.involvedapps.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.involvedapps.data.models.ArticleData

@Database(entities = [ArticleData::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}