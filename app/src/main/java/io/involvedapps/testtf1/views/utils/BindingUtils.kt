package io.involvedapps.testtf1.views.utils

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.involvedapps.testtf1.R
import io.involvedapps.testtf1.models.Article
import io.involvedapps.testtf1.views.adapters.ArticlesAdapter
import java.text.SimpleDateFormat


@BindingAdapter("articles")
fun RecyclerView.bindItems(articles: List<Article>?) {
    val adapter = this.adapter
    if(adapter is ArticlesAdapter && articles != null) {
        adapter.setArticles(articles)
    }
}

@BindingAdapter("pictureUrl")
fun bindImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.empty_state)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("pictureUrlRounded")
fun bindImageRounded(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.empty_state)
        .centerCrop()
        .circleCrop()
        .into(imageView)
}

@BindingAdapter("htmlText")
fun bindHtmlText(textView: TextView, htmlContent: String) {
    val result = HtmlCompat.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY)
    textView.text = result
    textView.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("date")
fun bindDateText(textView: TextView, date: String) {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    val output: String = formatter.format(parser.parse(date))

/*
    val localDateTime: LocalDateTime = LocalDateTime.parse(date)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    val output: String = formatter.format(localDateTime)
*/
    textView.text = output
}