package dev.seriy0904.valorantapi.ui.recylerview.calloutsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.api.Callouts

class CalloutsList : RecyclerView.Adapter<CalloutsList.CalloutsViewHolder>() {
    private val CalloutsList: ArrayList<Callouts> = arrayListOf()

    class CalloutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setList(newList: ArrayList<Callouts>) {
        CalloutsList.clear()
        CalloutsList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalloutsViewHolder {
        return CalloutsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.map_callouts_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CalloutsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = CalloutsList.size
}