package org.chemk.thesis.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentMessengerBinding

class MessengerFragment : Fragment(R.layout.fragment_messenger) {

    private lateinit var binding: FragmentMessengerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessengerBinding.bind(view)
    }

}