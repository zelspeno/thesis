package org.chemk.thesis.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentTabsBinding
import org.chemk.thesis.screens.database.accounts.entities.Account
import org.chemk.thesis.screens.utils.USER
import org.chemk.thesis.screens.utils.initUser

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        USER = Account()
        initUser()
    }

}