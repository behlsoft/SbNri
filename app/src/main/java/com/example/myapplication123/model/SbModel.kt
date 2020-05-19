package com.example.myapplication123.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos")
data class SbModel(


    @PrimaryKey @field:SerializedName("id")val id: Int,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("name") val name: String?,

    @field:SerializedName("open_issues_count") val open_issues_count: Int?,
    @field:SerializedName("permissions") @Embedded val permissions: Permissions?,
    @field:SerializedName("license") @Embedded val license: License?

)