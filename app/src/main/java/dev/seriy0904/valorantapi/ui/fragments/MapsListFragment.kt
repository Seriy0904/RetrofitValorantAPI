package dev.seriy0904.valorantapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.seriy0904.valorantapi.api.RetrofitUtil
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.ui.recylerview.mapsList.MapsListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MapsListFragment : Fragment() {
    private lateinit var mapRecyclerView: RecyclerView
    private lateinit var mapListSwipe: SwipeRefreshLayout

    private val mainListAdapter = MapsListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mapRecyclerView.adapter = mainListAdapter
        getMaps()
        mapListSwipe.setOnRefreshListener {
            getMaps()
            mapListSwipe.isRefreshing = false
        }
    }

    private fun getMaps(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val request = RetrofitUtil.retrofit.getMaps()
                val agents = request.data as ArrayList
                for (el in agents.indices) {
                    if (agents[el].displayIcon == null) {
                        agents.removeAt(el)
                        break
                    }
                }
                mainListAdapter.setList(agents)
            } catch (e: UnknownHostException) {
                Toast.makeText(requireContext(), "Check internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frView = inflater.inflate(R.layout.fragment_agent_list, container, false)
        mapRecyclerView = frView.findViewById(R.id.main_list_recycler_view)
        mapListSwipe = frView.findViewById(R.id.main_list_refresh)
        return frView
    }
}