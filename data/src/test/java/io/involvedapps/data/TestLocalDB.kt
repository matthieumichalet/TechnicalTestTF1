package io.involvedapps.data

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.involvedapps.data.db.AppDatabase
import io.involvedapps.data.db.ArticlesDao
import io.involvedapps.data.models.ArticleData
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SimpleEntityAddAndClearTest {

    private lateinit var articlesDao: ArticlesDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        articlesDao = db.getArticlesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addArticlesAndClear() = runBlocking {
        val article = ArticleData(
            id = "testId",
            title = "testtitle",
            webUrl = "testWeburl",
            webPublicationDate = "testWebPublicationDate",
            imageUrl = "testImageUrl",
            content = "testBody"
        )

        articlesDao.saveArticle(article)
        assertThat(articlesDao.getArticles().size, equalTo(1))
        articlesDao.clear()
        assertThat(articlesDao.getArticles().size, equalTo(0))
    }
}