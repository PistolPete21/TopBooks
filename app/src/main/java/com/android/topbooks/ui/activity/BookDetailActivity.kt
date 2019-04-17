package com.android.topbooks.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.android.topbooks.R
import com.android.topbooks.model.response.Book
import com.android.topbooks.ui.adapter.BookSellerAdapter
import com.android.topbooks.utility.BOOK_INTENT
import com.bumptech.glide.Glide

class BookDetailActivity : AppCompatActivity() {
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.android.topbooks.R.layout.activity_book_detail)

        if (intent != null) {
            book = intent?.extras?.getSerializable(BOOK_INTENT) as Book
        }

        setupUi(book)
    }

    private fun setupUi(book: Book) {
        val title = findViewById<TextView>(R.id.book_detail_title)
        val description = findViewById<TextView>(R.id.book_detail_description)

        title.text = book.title
        description.text = book.description

        val image = findViewById<ImageView>(R.id.book_detail_image)

        Glide.with(this)
            .load(book.book_image)
            .into(image)

        val recyclerView = findViewById<RecyclerView>(com.android.topbooks.R.id.book_sellers_list)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        if (recyclerView.adapter == null) {
            val adapter = BookSellerAdapter(book.buy_links)
            recyclerView.adapter = adapter
        }
    }
}