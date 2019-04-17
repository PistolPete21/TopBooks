package com.android.topbooks.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.topbooks.R
import com.android.topbooks.model.response.BuyLink
import kotlinx.android.synthetic.main.book_seller_item.view.*

class BookSellerAdapter(private val buyLinks:List<BuyLink>) : RecyclerView.Adapter<BookSellerAdapter.SellerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_seller_item, parent, false)
        return SellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        val buyLink:BuyLink = buyLinks[position]
        holder.bind(buyLink)
    }

    override fun getItemCount(): Int {
        return buyLinks.size
    }

    class SellerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val sellerName = view.book_sellers_name
        private val sellerUrl = view.book_sellers_url

        fun bind(buyLink: BuyLink) {
            sellerName.text = buyLink.name
            sellerUrl.text = buyLink.url
        }
    }
}