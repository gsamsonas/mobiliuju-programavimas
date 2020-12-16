package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentGesturesAndMediaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GesturesAndMediaFragment : Fragment() {

    private lateinit var binding: FragmentGesturesAndMediaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGesturesAndMediaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}