package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentMainBinding
import com.gsamsonas.mobiliujuprogramavimas.recycleradapters.SelectLabAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupRecycler(binding.rvLabList)
        return binding.root
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.adapter = SelectLabAdapter(listOf(
            Pair("Antras laboratorinis darbas") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDynamicTextFragment())
            },
            Pair("Trecias lab: Grafine vartotojo sasaja") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToGraphicalInformationInputFragment())
            },
            Pair("Ketvirtas lab: Leidimai") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToWebViewFragment())
            },
            Pair("Penktas lab: Fragmentai") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToListViewFragment())
            },
            Pair("Šeštas lab: Meniu ir gijos") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToMenuAndThreadsFragment())
            },
            Pair("Septintas lab: Pranešimai ir jų perėmimas") {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToBatteryNotificationFragment())
            }
        ))
    }
}