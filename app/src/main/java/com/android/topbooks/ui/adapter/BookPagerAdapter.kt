package com.android.topbooks.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.android.topbooks.model.response.Category
import com.android.topbooks.model.response.Response
import com.android.topbooks.ui.fragment.BookListFragment

class BookPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private lateinit var categories: List<Category>
    private lateinit var response: Response

    fun setData(response: Response) {
        this.categories = response.results.lists
        this.response = response
    }

    override fun getItem(position: Int): Fragment {
        return BookListFragment.newInstance(response.results.lists[position].books)
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return this.categories[position].list_name
    }
}