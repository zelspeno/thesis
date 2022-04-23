package org.chemk.thesis.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentJournalBinding

class JournalFragment : Fragment(R.layout.fragment_journal) {

    private lateinit var binding: FragmentJournalBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJournalBinding.bind(view)
    }

}