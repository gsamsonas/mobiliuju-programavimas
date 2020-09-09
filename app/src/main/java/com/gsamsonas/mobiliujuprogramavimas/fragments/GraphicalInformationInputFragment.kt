package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentGraphicalInformationInputBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.GraphicalInformationInputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphicalInformationInputFragment : Fragment() {

    private val viewModel: GraphicalInformationInputViewModel by viewModels()

    private lateinit var binding: FragmentGraphicalInformationInputBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphicalInformationInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}