package com.android.topbooks.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Book (
    val age_group: String,
    val amazon_product_url: String,
    val article_chapter_link: String,
    val author: String,
    val book_image: String,
    val book_image_width: Int,
    val book_image_height: Int,
    val book_review_link: String,
    val contributor: String,
    val contributor_note: String,
    val created_date: String,
    val description: String,
    val first_chapter_link: String,
    val price: Int,
    val primary_isbn10: String,
    val primary_isbn13: String,
    val publisher: String,
    val rank: Int,
    val rank_last_week: Int,
    val sunday_review_link: String,
    val title: String,
    val update_date: String,
    val weeks_on_list: String,
    @field:Json(name = "buy_links") val buy_links: List<BuyLink>
) : Serializable