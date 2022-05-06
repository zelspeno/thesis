package org.chemk.thesis.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.ActivityMainBinding
import org.chemk.thesis.screens.models.Repositories

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() // закрыть верхнюю плашку с названием приложения

        prepareToLogin(isSignedIn(), getRootNavController())
    }

    private fun prepareToLogin(isSignedIn: Boolean, navController: NavController) {
        val graph = navController.navInflater.inflate(R.navigation.main_graph)
        if (isSignedIn) {
            graph.setStartDestination(R.id.tabsFragment)
        } else graph.setStartDestination(R.id.signInFragment)
        navController.graph = graph
    }

    private fun isSignedIn(): Boolean {
        val bundle = intent.extras ?: throw IllegalStateException("No required arguments")
        val args = MainActivityArgs.fromBundle(bundle)
        return args.isSignedIn
    }

    private fun getRootNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.splashContainer) as NavHostFragment
        return navHost.navController
    }
}