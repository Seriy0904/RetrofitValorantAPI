package dev.seriy0904.valorantapi.ui.recylerview.abilitiesList

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.api.Ability
import dev.seriy0904.valorantapi.R

class AbilitiesListAdapter() : RecyclerView.Adapter<AbilitiesListAdapter.AbilitiesViewHolder>() {
    private val abilityList = arrayListOf<Ability>()

    class AbilitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abilityIcon: ImageView = itemView.findViewById(R.id.abilityIcon)
        val abilitySymbol: TextView = itemView.findViewById(R.id.abilitySymbol)
        val abilityName: TextView = itemView.findViewById(R.id.abilityName)
        val abilityDescription: TextView = itemView.findViewById(R.id.abilityDescription)
    }

    fun setList(newList: ArrayList<Ability>) {
        abilityList.clear()
        abilityList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        return AbilitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ability_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val abilities = abilityList[position]
        holder.abilityName.text = abilities.displayName
        val normalText = abilities.description
        val fullText = normalText.replace("ОГОНЬ", "<b><font color=#FF0000>ОГОНЬ</font></b>")
            .replace("МГНОВЕННО", "<b><font color=#FF0000>МГНОВЕННО</font></b>")
            .replace("АКТИВАЦИИ", "<b><font color=#FF0000>АКТИВАЦИИ</font></b>")
        holder.abilityDescription.text = Html.fromHtml(fullText)
        if (abilities.slot != "Passive") {
            Glide.with(holder.itemView.context).load(abilities.displayIcon).into(holder.abilityIcon)
        } else {
            holder.abilitySymbol.visibility = View.VISIBLE
            holder.abilityIcon.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount() = abilityList.size
}