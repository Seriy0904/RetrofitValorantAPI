package dev.seriy0904.valorantapi.ui.recylerview.mapsList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.api.MapInfo
import dev.seriy0904.valorantapi.ui.SelectedMapActivity

class MapsListAdapter() : RecyclerView.Adapter<MapsListAdapter.MapsViewHolder>() {
    private val mapList: ArrayList<MapInfo> = arrayListOf()

    class MapsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mapImage: ImageView = itemView.findViewById(R.id.map_list_item_photo)
        val mapName: TextView = itemView.findViewById(R.id.map_list_item_name)
    }

    fun setList(newList:ArrayList<MapInfo>){
        mapList.clear()
        mapList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder {
        return MapsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.map_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        val mapInfo = mapList[position]
        Glide.with(holder.itemView.context).load(mapInfo.listViewIcon).into(holder.mapImage)
        holder.mapName.text = mapInfo.displayName
        holder.itemView.setOnClickListener {
            val mapIntent = Intent(it.context,SelectedMapActivity::class.java)
            mapIntent.putExtra("MapInfo",mapInfo)
            it.context.startActivity(mapIntent)
        }
    }

    override fun getItemCount() = mapList.size
}