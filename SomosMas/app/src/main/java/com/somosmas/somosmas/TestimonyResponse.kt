package com.somosmas.somosmas

import com.google.gson.annotations.SerializedName


data class TestimonyResponse(
    @SerializedName("data") val data: List<DataTestimony>,
    @SerializedName("message")val message: String,
    @SerializedName("success")val success: Boolean
)

data class DataTestimony(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("deleted_at") val deleted_at: Any,
    @SerializedName("description") val description: String,
    @SerializedName("group_id") val group_id: Any,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("updated_at") val updated_at: String
)