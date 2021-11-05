package dev.seriy0904.valorantapi.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.api.Ability
import dev.seriy0904.valorantapi.api.Data
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.ui.recylerview.abilitiesList.AbilitiesListAdapter

class SelectedAgentActivity : AppCompatActivity() {
    private val adapter = AbilitiesListAdapter()
    private val agentInfo: Data by lazy { intent.getSerializableExtra("AgentInfo") as Data }
    private val mplayer = MediaPlayer()

    //View initialization
    private val agentIcon: ImageView by lazy { findViewById(R.id.selected_agent_icon) }
    private val agentName: TextView by lazy { findViewById(R.id.selected_agent_name) }
    private val agentDescritpion: TextView by lazy { findViewById(R.id.selected_agent_description) }
    private val agentRoleIcon: ImageView by lazy { findViewById(R.id.selected_agent_role_icon) }
    private val agentRoleName: TextView by lazy { findViewById(R.id.selected_agent_role_name) }
    private val agentRoleDescription: TextView by lazy { findViewById(R.id.selected_agent_role_description) }
    private val abilityRecycler: RecyclerView by lazy { findViewById(R.id.selected_ability_list) }
    private val agentPoster: ImageView by lazy { findViewById(R.id.selected_poster) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_agent)
        supportActionBar?.title = agentInfo.displayName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        agentName.text = agentInfo.displayName
        agentDescritpion.apply {
            text = agentInfo.description
            setOnClickListener {
                maxLines = if (maxLines == 3) 2000 else 3
            }
        }
        agentRoleName.text = agentInfo.role?.displayName
        agentRoleDescription.text = agentInfo.role?.description
        Glide.with(this).load(agentInfo.displayIcon).into(agentIcon)
        Glide.with(this).load(agentInfo.role?.displayIcon).into(agentRoleIcon)
        Glide.with(this).load(agentInfo.bustPortrait).into(agentPoster)
        abilityRecycler.layoutManager = LinearLayoutManager(this)
        abilityRecycler.adapter = adapter
        adapter.setList(agentInfo.abilities as ArrayList<Ability>)
        playAudio(0)
    }

    override fun onStop() {
        mplayer.stop()
        super.onStop()
    }

    private fun playAudio(index: Int) {
        try {
            mplayer.setDataSource(agentInfo.voiceLine.mediaList[index].wave)
            mplayer.setOnCompletionListener {
            }
            mplayer.prepareAsync()
            mplayer.setOnPreparedListener {
                it.start()
            }
        } catch (i: IllegalStateException) {
            Toast.makeText(this, "Подождите окончания воспроизведения", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home-> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}