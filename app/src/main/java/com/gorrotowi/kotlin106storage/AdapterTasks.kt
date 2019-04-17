package com.gorrotowi.kotlin106storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorrotowi.kotlin106storage.roomdatabase.EntityTODO
import kotlinx.android.synthetic.main.item_tasks.view.*
import kotlin.properties.Delegates

class AdapterTasks : RecyclerView.Adapter<AdapterTasks.ViewHolder>() {

    var dataList: List<EntityTODO> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    private lateinit var clickListener: (Int, EntityTODO) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList[position])
        holder.itemView.setOnClickListener {
            clickListener(position, dataList[position])
        }
    }

    fun setListener(listener: (Int, EntityTODO) -> Unit) {
        clickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data: EntityTODO) {
            itemView.txtItemTitle?.text = data.title
            itemView.txtItemID?.text = "${data.id}"
        }
    }
}