package org.chemk.thesis.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentSplashBinding
import org.chemk.thesis.screens.main.MainActivity
import kotlin.concurrent.timer

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        renderAnimations()
        
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)

    }
    private fun renderAnimations() {
        binding.loadingProgressBar.alpha = 0f
        binding.loadingProgressBar.animate()
            .alpha(0.7f)
            .setDuration(1000)
            .start()

        binding.loadingTextView.alpha = 0f
        binding.loadingTextView.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(1000)
            .start()

    }
}