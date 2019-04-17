package com.gorrotowi.kotlin105fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorrotowi.kotlin105fragments.R
import com.gorrotowi.kotlin105fragments.entitys.Email
import kotlinx.android.synthetic.main.item_mail.view.*
import kotlin.properties.Delegates

class AdapterMailList : RecyclerView.Adapter<AdapterMailList.ViewHolder>() {

    var dataList: List<Email> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    private lateinit var clickListener: (Int, Email) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mail,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList[position])
        holder.itemView.setOnClickListener { clickListener.invoke(position, dataList[position]) }
    }

    fun setListener(func: (Int, Email) -> Unit) {
        clickListener = func
    }

    fun setData(listEmail: List<Email>) {
        dataList = listEmail
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(email: Email) {
            with(itemView) {
                txtItemTitleMail?.text = email.title
                txtItemSubjectMail?.text = email.subject
            }
        }
    }
}