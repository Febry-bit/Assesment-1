package org.d3if2015.konversisatuan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAboutBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = SaranGridAdapter()

        return binding.root
    }
}