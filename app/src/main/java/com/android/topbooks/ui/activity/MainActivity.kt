package com.android.topbooks.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import com.android.topbooks.R
import com.android.topbooks.model.request.LoadBooksRequest
import com.android.topbooks.model.response.Response
import com.android.topbooks.ui.adapter.BookPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var loadBooksRequest: LoadBooksRequest

    private lateinit var progressIndicator:ProgressBar
    private lateinit var errorMessage:TextView

    private lateinit var viewPager:ViewPager
    private lateinit var tabLayout:TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        progressIndicator = findViewById(R.id.main_activity_progress_indicator)
        errorMessage = findViewById(R.id.main_activity_error_message)

        viewPager = findViewById(R.id.book_viewpager)
        tabLayout = findViewById(R.id.book_tab_layout)

        // Kick off the data call
        loadBooksRequest = LoadBooksRequest(this)
        loadBooksRequest.loadBooksByCategories()

    }

    fun dataFinished() {
        errorMessage.visibility = GONE
        progressIndicator.visibility = GONE
        viewPager.visibility = VISIBLE
    }

    fun dataLoading() {
        errorMessage.visibility = GONE
        viewPager.visibility = GONE
        progressIndicator.visibility = VISIBLE
    }

    fun dataError() {
        progressIndicator.visibility = GONE
        viewPager.visibility = GONE
        errorMessage.visibility = VISIBLE
    }

    fun updateAdapter(response:Response) {
        val viewPagerAdapter = BookPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setData(response)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = viewPagerAdapter.count / 2
        tabLayout.setupWithViewPager(viewPager)
    }
}
