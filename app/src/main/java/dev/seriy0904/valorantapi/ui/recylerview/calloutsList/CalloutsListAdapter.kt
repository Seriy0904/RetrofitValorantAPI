package dev.seriy0904.valorantapi.ui.recylerview.calloutsList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.api.Callouts

class CalloutsListAdapter : RecyclerView.Adapter<CalloutsListAdapter.CalloutsViewHolder>() {
    private val CalloutsList: ArrayList<Callouts> = arrayListOf()

    class CalloutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val regionName:TextView = itemView.findViewById(R.id.mapRegionName)
        val superRegionName:TextView = itemView.findViewById(R.id.mapSuperRegionName)
    }

    fun setList(newList: ArrayList<Callouts>) {
        if(newList != CalloutsList){
            CalloutsList.clear()
            CalloutsList.addAll(sortCallouts(newList))
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalloutsViewHolder {
        return CalloutsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.map_callouts_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CalloutsViewHolder, position: Int) {
        if(position>0&&CalloutsList[position]!=CalloutsList[position-1]){
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(16)
            holder.itemView.layoutParams = params
            Log.d("MyTag","Params")
        }
        holder.regionName.text = CalloutsList[position].regionName
        holder.superRegionName.text = CalloutsList[position].superRegionName
    }

    override fun getItemCount() = CalloutsList.size

    private fun sortCallouts(list:ArrayList<Callouts>):ArrayList<Callouts>{
        for (i in list.indices){
            for ( y in 0..list.size-2){
                if(list[y].superRegionName>list[y+1].superRegionName){
                    val buf = list[y].superRegionName
                    list[y].superRegionName = list[y+1].superRegionName
                    list[y+1].superRegionName = buf
                }
            }
        }
        return list
    }
}