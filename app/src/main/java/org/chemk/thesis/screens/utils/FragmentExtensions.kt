package org.chemk.thesis.screens.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.chemk.thesis.R

fun Fragment.findTopNavController(): NavController {
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.splashContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}