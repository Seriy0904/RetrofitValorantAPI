package dev.seriy0904.valorantapi.recylerview.mainList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.R

class MainListAdapter() : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    private val mainListModels: ArrayList<MainListModel> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val agent_name = itemView.findViewById<TextView>(R.id.mainlist_item_name)
        val agent_icon = itemView.findViewById<ImageView>(R.id.mainlist_item_icon)
        val agent_description = itemView.findViewById<TextView>(R.id.mainlist_item_description)
    }

    fun setList(newList: ArrayList<MainListModel>) {
        mainListModels.clear()
        mainListModels.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mainlist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModel = mainListModels[position]
        Glide.with(holder.itemView.context).load(itemModel.agentIcon).into(holder.agent_icon)
        holder.agent_name.text = itemModel.agentName
        holder.agent_description.text = itemModel.agentDescription
    }

    override fun getItemCount() = mainListModels.size
}