package dev.seriy0904.valorantapi.ui.recylerview.mainList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.api.Data
import dev.seriy0904.valorantapi.ui.SelectedAgentActivity

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    private val mainListModels: ArrayList<Data> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val agentName: TextView = itemView.findViewById(R.id.mainlist_item_name)
        val agentIcon: ImageView = itemView.findViewById(R.id.mainlist_item_icon)
        val agentDescription: TextView = itemView.findViewById(R.id.mainlist_item_description)
    }

    fun isLoaded(): Boolean{
        return mainListModels.size>0
    }

    fun setList(newList: ArrayList<Data>) {
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
        Glide.with(holder.itemView.context).load(itemModel.displayIconSmall).into(holder.agentIcon)
        holder.agentName.text = itemModel.displayName
        holder.agentDescription.text = itemModel.description
        holder.itemView.setOnClickListener {
            val selectedAgentIntent = Intent(it.context,SelectedAgentActivity::class.java)
            selectedAgentIntent.putExtra("AgentInfo",itemModel)
            it.context.startActivity(selectedAgentIntent)
        }
    }

    override fun getItemCount() = mainListModels.size
}