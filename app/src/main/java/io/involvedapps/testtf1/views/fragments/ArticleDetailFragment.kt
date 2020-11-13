package io.involvedapps.testtf1.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.involvedapps.testtf1.R
import io.involvedapps.testtf1.databinding.FragmentArticleDetailBinding
import io.involvedapps.testtf1.viewmodels.ArticleDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailFragment: Fragment() {

    private val viewModel: ArticleDetailViewModel by viewModel()
    private lateinit var viewBinding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        viewBinding.viewModel = this.viewModel
        viewBinding.lifecycleOwner = this

        val articleId = arguments?.getString("articleId") ?: ""
        viewModel.getArticleDetail(articleId)

        val backButton =  viewBinding.root.findViewById<View>(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return viewBinding.root
    }

}
