package id.haadii.visionplus.xiaomiwebview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.haadii.visionplus.xiaomiwebview.R
import id.haadii.visionplus.xiaomiwebview.model.Data
import id.haadii.visionplus.xiaomiwebview.model.Episode
import id.haadii.visionplus.xiaomiwebview.model.XiaomiResponse
import kotlinx.android.synthetic.main.item_content.view.*

/**
 * Created by nurrahmanhaadii on 15,July,2020
 */
class ListAdapter(private val items: List<Episode>, private val itemClick: (Episode) -> Unit)
    : RecyclerView.Adapter<ListAdapter.ListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ListHolder(view, items, itemClick)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ListHolder(view: View, data: List<Episode>, val itemClick: (Episode) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: Episode) {
            itemView.tvTitle.text = item.title
            itemView.setOnClickListener { itemClick(item) }
        }
    }
}