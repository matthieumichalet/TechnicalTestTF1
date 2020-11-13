package io.involvedapps.testtf1.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.involvedapps.testtf1.databinding.ItemArticleBinding
import io.involvedapps.testtf1.models.Article

typealias OnClickArticle = (article: Article) -> Unit

class ArticlesAdapter(
    private val onClickArticle: OnClickArticle
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var articles: ArrayList<Article> = arrayListOf()

    fun setArticles(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        this.notifyDataSetChanged()
    }

    class ArticleViewHolder(
        private var binding: ItemArticleBinding,
        view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemArticleBinding = ItemArticleBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(
            itemBinding,
            itemBinding.root
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onClickArticle(article)
        }
    }

}