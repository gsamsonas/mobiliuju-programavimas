package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gsamsonas.mobiliujuprogramavimas.R
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
        setupAutoComplete(binding.actvFaculty)
        setupSpinner(binding.spinnerWeekDay)
        return binding.root
    }

    private fun setupAutoComplete(autoCompleteTextView: AutoCompleteTextView) {
        ArrayAdapter.createFromResource(
            autoCompleteTextView.context,
            R.array.faculty_list,
            android.R.layout.simple_dropdown_item_1line
        ).also {
            autoCompleteTextView.setAdapter(it)
        }
    }

    private fun setupSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            spinner.context,
            R.array.week_days,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
    }
}