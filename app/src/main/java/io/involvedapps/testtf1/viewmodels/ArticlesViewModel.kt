package io.involvedapps.testtf1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.involvedapps.domain.usecases.ArticlesUseCases
import io.involvedapps.testtf1.models.Article
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val articlesUseCases: ArticlesUseCases
): ViewModel() {

    val articles = MutableLiveData<List<Article>>()

    fun getArticles() {
        viewModelScope.launch {
            val newArticles = Article.mapFromEntities(articlesUseCases.getArticles())
            articles.postValue(newArticles)
        }
    }

}