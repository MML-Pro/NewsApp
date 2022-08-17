package com.mml.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mml.newsapp.R
import com.mml.newsapp.databinding.FragmentArticleBinding
import com.mml.newsapp.ui.MainActivity
import com.mml.newsapp.viewmodels.NewsViewModel


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentArticleBinding

    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentArticleBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()

            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = false
            webChromeClient = WebChromeClient()

            if (article.url.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "URl is null", Toast.LENGTH_SHORT).show()
            } else {
                loadUrl(article.url)
            }

        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(args.article)
            Snackbar.make(it, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}