package io.involvedapps.testtf1.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.involvedapps.testtf1.R
import io.involvedapps.testtf1.databinding.FragmentHomeBinding
import io.involvedapps.testtf1.models.Article
import io.involvedapps.testtf1.viewmodels.ArticlesViewModel
import io.involvedapps.testtf1.views.adapters.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private val viewModel: ArticlesViewModel by viewModel()
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ArticlesAdapter { article -> onClickArticle(article) }

        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        viewBinding.viewModel = this.viewModel
        viewBinding.lifecycleOwner = this

        viewModel.getArticles()

        return viewBinding.root
    }

    private fun onClickArticle(article: Article) {
        findNavController().navigate(R.id.action_article_detail, bundleOf("articleId" to article.id))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.articlesListView.layoutManager = LinearLayoutManager(context)
        viewBinding.articlesListView.adapter = adapter


    }

}