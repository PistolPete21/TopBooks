package com.android.topbooks.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.topbooks.R
import com.android.topbooks.model.response.Book
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(private val clickHandler: BookListAdapterOnClickHandler, private val bookList:List<Book>): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    interface BookListAdapterOnClickHandler {
        fun onClick(book:Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BookListAdapter.BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListAdapter.BookViewHolder, position: Int) {
        val book:Book = bookList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class BookViewHolder(private var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val context = view.context
        private val title = view.book_title
        private val author = view.book_author
        private val publisher = view.book_publisher
        private val image = view.book_image

        fun bind(book:Book) {
            title.text = book.title
            author.text = book.author
            publisher.text = book.publisher
            Glide.with(context)
                .load(book.book_image)
                .into(image)

            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val selectedBook:Book = bookList[adapterPosition]
            clickHandler.onClick(selectedBook)
        }
    }
}