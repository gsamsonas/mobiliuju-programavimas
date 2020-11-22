package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentMenuAndThreadsBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.MenuAndThreadsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuAndThreadsFragment : Fragment() {

    private val viewModel: MenuAndThreadsViewModel by viewModels()

    private lateinit var binding: FragmentMenuAndThreadsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuAndThreadsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}