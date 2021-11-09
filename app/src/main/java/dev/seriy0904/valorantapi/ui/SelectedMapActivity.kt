package dev.seriy0904.valorantapi.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.api.MapInfo
import dev.seriy0904.valorantapi.ui.recylerview.calloutsList.CalloutsListAdapter

class SelectedMapActivity : AppCompatActivity() {
    private val calloutsAdapter = CalloutsListAdapter()
    private val mapScheme:ImageView by lazy { findViewById(R.id.map_scheme_photo) }
    private val mapSplash:ImageView by lazy { findViewById(R.id.map_splash_photo) }
    private val mapName:TextView by lazy { findViewById(R.id.map_name) }
    private val calloutsRecycler:RecyclerView by lazy { findViewById(R.id.calloutsRecycler) }
    private val mapInfo: MapInfo by lazy { intent.getSerializableExtra("MapInfo") as MapInfo }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_map)
        Glide.with(this).load(mapInfo.displayIcon).into(mapScheme)
        Glide.with(this).load(mapInfo.splash).into(mapSplash)
//        FileUtils.openBitmapInputStream()
        mapName.text = mapInfo.displayName
        calloutsRecycler.layoutManager = GridLayoutManager(this,2)
        calloutsRecycler.adapter = calloutsAdapter
        calloutsAdapter.setList(mapInfo.callouts as ArrayList)
    }

}