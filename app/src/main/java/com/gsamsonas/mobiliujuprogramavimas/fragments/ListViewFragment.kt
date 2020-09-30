package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentListViewBinding
import com.gsamsonas.mobiliujuprogramavimas.recycleradapters.SelectLabAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewFragment : Fragment() {

    private lateinit var binding: FragmentListViewBinding

    private val items = listOf(
        "Tekstas su A raidėmis",
        "Be ieškomo simbolio",
        "Čia yra ieškomas simbolis",
        "Nieko nebus",
        "Rasi ko ieškai"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(binding.rvItems)
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        // Reusing adapter, too lazy
        recyclerView.adapter = SelectLabAdapter(
            items.map { Pair(it) {
                val fragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flFragment, ListViewChildFragment.get(it))
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            } }
        )
    }
}