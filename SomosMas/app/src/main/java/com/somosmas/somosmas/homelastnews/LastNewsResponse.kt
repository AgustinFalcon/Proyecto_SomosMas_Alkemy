package com.somosmas.somosmas.homelastnews

import com.google.gson.annotations.SerializedName

data class LastNewsResponse(
    @SerializedName("succes")val succes: Boolean,
    @SerializedName("data")val data: List<DataLastNews>)

data class DataLastNews(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: Any,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String,
    @SerializedName("user_id") val user_id: Any,
    @SerializedName("category_id") val category_id: Any,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("deleted_at") val deleted_at: Any)