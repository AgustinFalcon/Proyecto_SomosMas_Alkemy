package com.somosmas.somosmas

import com.google.gson.annotations.SerializedName

//incorpored @SerializedName to all atributes


data class SlideResponse(

    @SerializedName("data") val data: List<Data>,
    @SerializedName("message")val message: String,
    @SerializedName("success")val success: Boolean
    )

data class Data(
    @SerializedName("created_at")val created_at: String,
    @SerializedName("deleted_at") val deleted_at: Any,
    @SerializedName("description") val description: String,
    @SerializedName("group_id") val group_id: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user_id") val user_id: Int
)