package com.android.topbooks.model.response

import java.io.Serializable

data class BuyLink(
    val name: String,
    val url: String
) : Serializable