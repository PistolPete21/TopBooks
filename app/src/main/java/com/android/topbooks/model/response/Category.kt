package com.android.topbooks.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Category (
    val list_id: Int,
    val list_name: String,
    val list_name_encoded: String,
    val display_name: String,
    val updated: String,
    val list_image: String,
    val list_image_width: Int,
    val list_image_height: Int,
    @field:Json(name = "books") val books: List<Book>
) : Serializable