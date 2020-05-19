package com.example.myapplication123.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class License constructor(
    @SerializedName("name") @ColumnInfo(name = "licence_name") val name: String
)