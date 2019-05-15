package com.gorrotowi.kotlin109network.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorrotowi.kotlin109network.R
import com.gorrotowi.kotlin109network.entities.ItemBook
import kotlinx.android.synthetic.main.item_book.view.*
import kotlin.properties.Delegates

class AdapterBooks : RecyclerView.Adapter<AdapterBooks.ViewHolderBooks>() {

    var sourceData: List<ItemBook?> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBooks {
        return ViewHolderBooks(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_book,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = sourceData.size

    override fun onBindViewHolder(holder: ViewHolderBooks, position: Int) {
        sourceData[position]?.let {
            holder.bindView(it)
        }
    }

    class ViewHolderBooks(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data: ItemBook) {
            with(data) {
                itemView.txtBook?.text = book
                itemView.txtLastPrice?.text = price
            }
        }
    }
}