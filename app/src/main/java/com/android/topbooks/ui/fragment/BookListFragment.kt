package com.android.topbooks.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.android.topbooks.model.response.Book
import com.android.topbooks.ui.activity.BookDetailActivity
import com.android.topbooks.ui.adapter.BookListAdapter
import com.android.topbooks.utility.BOOKS_LIST_INTENT
import com.android.topbooks.utility.BOOK_INTENT
import java.io.Serializable

class BookListFragment : Fragment(), BookListAdapter.BookListAdapterOnClickHandler {
    private lateinit var books:List<Book>

    companion object {
        fun newInstance(books: List<Book>): BookListFragment {
            val fragment = BookListFragment()

            val bundle = Bundle().apply {
                putSerializable(BOOKS_LIST_INTENT, books as Serializable)
            }

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) books = arguments!!.getSerializable(BOOKS_LIST_INTENT) as List<Book>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.android.topbooks.R.layout.book_list_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(com.android.topbooks.R.id.book_list)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        if (recyclerView.adapter == null) {
            val adapter = BookListAdapter(this, books)
            recyclerView.adapter = adapter
        }

        return view
    }

    override fun onClick(book: Book) {
        val intent = Intent(activity, BookDetailActivity::class.java)
        intent.putExtra(BOOK_INTENT, book)
        startActivity(intent)
    }
}