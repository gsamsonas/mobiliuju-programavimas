package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentStaticTextAnalysisBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.StaticTextAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StaticTextAnalysisFragment : Fragment() {

    private val viewModel: StaticTextAnalysisViewModel by viewModels()

    private lateinit var binding: FragmentStaticTextAnalysisBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStaticTextAnalysisBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}