package com.example.myapplication123.retrofit

import com.example.myapplication123.model.SbModel
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<SbModel> = emptyList(),
    val nextPage: Int? = null
)