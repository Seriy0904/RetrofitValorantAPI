package dev.seriy0904.valorantapi.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.Api.Data
import dev.seriy0904.valorantapi.R

class SelectedAgentActivity : AppCompatActivity() {
    private val agentInfo: Data by lazy { intent.getSerializableExtra("AgentInfo") as Data }
    private val agentIcon: ImageView by lazy { findViewById(R.id.selected_agent_icon) }
    private val agentName: TextView by lazy { findViewById(R.id.selected_agent_name) }
    private val agentDescription: TextView by lazy { findViewById(R.id.selected_agent_description) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_agent)
        Glide.with(this).load(agentInfo.displayIcon).into(agentIcon)
        agentName.text = agentInfo.displayName
        agentDescription.text = agentInfo.description
    }

}