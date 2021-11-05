package dev.seriy0904.valorantapi.ui.fragments

import android.os.Bundle
import android.util.Log
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
import dev.seriy0904.valorantapi.ui.recylerview.mainList.MainListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class AgentListFragment : Fragment() {
    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var mainListSwipe: SwipeRefreshLayout
    private val mainListAdapter = MainListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mainRecyclerView.adapter = mainListAdapter
        loadList()
        mainListSwipe.setOnRefreshListener {
            loadList()
            mainListSwipe.isRefreshing = false
        }
    }

    override fun onResume() {
        Log.d("MyTag","Resume")
        super.onResume()
    }
    private fun loadList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val request = RetrofitUtil.retrofit.getAgents()
                val maps = request.data as ArrayList
                for (el in maps.indices) {
                    if (maps[el].role == null) {
                        maps.removeAt(el)
                        break
                    }
                }
                mainListAdapter.setList(maps)
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
        mainRecyclerView = frView.findViewById(R.id.main_list_recycler_view)
        mainListSwipe = frView.findViewById(R.id.main_list_refresh)
        return frView
    }
}