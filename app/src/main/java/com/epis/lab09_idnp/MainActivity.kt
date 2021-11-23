package com.epis.lab09_idnp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.epis.lab09_idnp.databinding.ActivityMainBinding
import com.epis.lab09_idnp.ui.dashboard.DashboardFragment
import com.epis.lab09_idnp.ui.home.HomeFragment
import com.epis.lab09_idnp.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val fragmentHome = HomeFragment()
        openFragment(fragmentHome)

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragmentHome = HomeFragment()
                    openFragment(fragmentHome)
                    true
                }
                R.id.navigation_dashboard -> {
                    val fragmentDashboard = DashboardFragment()
                    openFragment(fragmentDashboard)
                    true
                }
                R.id.navigation_notifications -> {
                    val fragmentNotifications = NotificationsFragment()
                    openFragment(fragmentNotifications)
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_fragment_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}