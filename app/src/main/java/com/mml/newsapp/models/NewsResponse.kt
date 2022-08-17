package com.mml.newsapp.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

    @SerializedName("articles")
    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) : Serializable