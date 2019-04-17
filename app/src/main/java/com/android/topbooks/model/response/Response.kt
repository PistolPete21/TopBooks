package com.android.topbooks.model.response

data class Response(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: Results
)