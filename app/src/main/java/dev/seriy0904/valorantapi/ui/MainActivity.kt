package dev.seriy0904.valorantapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.seriy0904.valorantapi.R
import dev.seriy0904.valorantapi.ui.fragments.AgentListFragment
import dev.seriy0904.valorantapi.ui.fragments.MapsListFragment

class MainActivity : AppCompatActivity() {
    private val agentListFragment = AgentListFragment()
    private val mapsListFragment = MapsListFragment()
    private val bottomNavView: BottomNavigationView by lazy { findViewById(R.id.main_bottom_nav) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_container, mapsListFragment)
            .add(R.id.main_fragment_container, agentListFragment)
            .hide(mapsListFragment).commit()
        supportActionBar?.title = "Агенты"
        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_agents -> {
                    setFragment(mapsListFragment, agentListFragment)
                    supportActionBar?.title = "Агенты"
                }
                R.id.bottom_nav_maps -> {
                    setFragment(agentListFragment, mapsListFragment)
                    supportActionBar?.title = "Карты"
                }
            }
            true
        }
    }

    private fun setFragment(hideFragment: Fragment,showFragment:Fragment) {
        supportFragmentManager.beginTransaction().hide(hideFragment).show(showFragment).commit()
    }
}