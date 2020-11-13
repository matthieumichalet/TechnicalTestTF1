package io.involvedapps.testtf1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.involvedapps.domain.usecases.ArticlesUseCases
import io.involvedapps.testtf1.models.Article
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val articlesUseCases: ArticlesUseCases
): ViewModel() {

    val articleDetail = MutableLiveData<Article?>()

    init {
        articleDetail.value = null
    }

    fun getArticleDetail(articleId: String) {
        viewModelScope.launch {
            articlesUseCases.getArticleDetail(articleId).let {
                val articleDetailed = Article.mapFromEntity(it)
                articleDetail.postValue(articleDetailed)
            }
        }
    }

}