package org.chemk.thesis.screens.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentMessengerBinding
import org.chemk.thesis.screens.messenger.*

class MessengerFragment : Fragment(R.layout.fragment_messenger) {

    private lateinit var binding: FragmentMessengerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessengerBinding.bind(view)

        with(binding) {
            workModeBtn.setOnClickListener {
                openWorkMode(context)
            }
            hostel1.setOnClickListener {
                HostelData1().show(childFragmentManager, "HostelData1")
            }
            hostel2.setOnClickListener {
                HostelData2().show(childFragmentManager, "HostelData2")
            }
            campus1.setOnClickListener {
                Campus1().show(childFragmentManager, "Campus1")
            }
            campus2.setOnClickListener {
                Campus2().show(childFragmentManager, "Campus2")
            }
            campus3.setOnClickListener {
                Campus3().show(childFragmentManager, "Campus3")
            }
            campus4.setOnClickListener {
                Campus4().show(childFragmentManager, "Campus4")
            }
            campus5.setOnClickListener {
                Campus5().show(childFragmentManager, "Campus5")
            }
        }
    }

    private fun openWorkMode(context: Context?) {
        val url = Uri.parse("http://www.chemk.org/images/sampledata/docs/svedenia/regim_rab_2019.pdf")
        val intent: Intent = Intent(Intent.ACTION_VIEW, url)
        context?.startActivity(intent)
    }

}