package com.example.myapplication123.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Permissions constructor(
    @SerializedName("admin") @ColumnInfo(name = "permission_admin") val admin: Boolean,
    @SerializedName("pull") @ColumnInfo(name = "permission_pull") val pull: Boolean,
    @SerializedName("push") @ColumnInfo(name = "permission_push")val push: Boolean
)