package org.chemk.thesis.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentSplashBinding
import org.chemk.thesis.screens.main.MainActivity
import org.chemk.thesis.screens.main.MainActivityArgs
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.utils.observeEvent
import kotlin.concurrent.timer

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModelCreator { SplashViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        renderAnimations()
        
        viewModel.launchMainScreenEvent.observeEvent(viewLifecycleOwner) {
            launchMainScreen(it)
        }

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

    private fun launchMainScreen(isSignedIn: Boolean) {

        val intent = Intent(requireContext(), MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = MainActivityArgs(isSignedIn)
        intent.putExtras(args.toBundle())
        startActivity(intent)
    }
}