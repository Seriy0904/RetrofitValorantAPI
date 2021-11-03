package dev.seriy0904.valorantapi.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.seriy0904.valorantapi.Api.RetrofitUtil
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.recylerview.mainList.MainListAdapter
import dev.seriy0904.valorantapi.recylerview.mainList.MainListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {
    private val mainRecyclerView: RecyclerView by lazy { findViewById(R.id.main_list_recycler_view) }
    private val mainListSwipe: SwipeRefreshLayout by lazy { findViewById(R.id.main_list_refresh) }
    private val mainListAdapter = MainListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = mainListAdapter
        loadList()
        mainListSwipe.setOnRefreshListener {
            loadList()
            mainListSwipe.isRefreshing = false
        }
    }

    private fun loadList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val agents = RetrofitUtil.retrofit.getAgents()
                val modelList = arrayListOf<MainListModel>()
                for (el in agents.data) {
                    modelList.add(
                        MainListModel(
                            el.displayName,
                            el.displayIconSmall,
                            el.description
                        )
                    )
                }
                mainListAdapter.setList(modelList)
            } catch (e: UnknownHostException) {
                Toast.makeText(this@MainActivity, "Check internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}