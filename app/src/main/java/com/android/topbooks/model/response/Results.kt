package com.android.topbooks.model.response

import com.squareup.moshi.Json

data class Results (
    val bestsellers_date: String,
    val published_date: String,
    val published_date_description: String,
    val previous_published_date: String,
    val next_published_date: String,
    @field:Json(name = "lists") val lists: List<Category>
)