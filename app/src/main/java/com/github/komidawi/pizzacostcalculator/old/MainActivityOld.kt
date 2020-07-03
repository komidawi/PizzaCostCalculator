package com.github.komidawi.pizzacostcalculator.old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.databinding.ActivityMainBinding
import com.github.komidawi.pizzacostcalculator.databinding.ActivityMainOldBinding

private const val TIMER_SECONDS_ELAPSED_KEY = "timer_seconds_elapsed"

class MainActivityOld : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainOldBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        setContentView(binding.root)

        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        timer = Timer(this.lifecycle)

        if (savedInstanceState != null) {
            timer.secondsElapsed = savedInstanceState.getInt(TIMER_SECONDS_ELAPSED_KEY)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TIMER_SECONDS_ELAPSED_KEY, timer.secondsElapsed)
    }
}
