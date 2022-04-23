package org.chemk.thesis.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding: FragmentNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
    }

}