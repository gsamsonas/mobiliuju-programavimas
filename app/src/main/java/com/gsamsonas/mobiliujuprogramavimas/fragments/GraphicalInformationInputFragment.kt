package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.core.EventObserver
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentGraphicalInformationInputBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.GraphicalInformationInputViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalTime

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAutoComplete(binding.actvFaculty)
        setupSpinner(binding.spinnerWeekDay)
        setupTimePicker(binding.timePicker)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onSave.observe(viewLifecycleOwner, EventObserver {
            AlertDialog.Builder(requireContext())
                .setTitle("Information output")
                .setMessage(it.toString())
                .setPositiveButton("Ok") { dialog, _ ->
                     dialog.dismiss()
                }
                .setCancelable(false)
                .create()
                .show()
        })
        viewModel.onError.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                GraphicalInformationInputViewModel.GraphicalInformationInputError.MissingTitle -> {
                    displayErrorMessage("Title is missing")
                }
                GraphicalInformationInputViewModel.GraphicalInformationInputError.MissingFaculty -> {
                    displayErrorMessage("Faculty is missing")
                }
                GraphicalInformationInputViewModel.GraphicalInformationInputError.MissingDay -> {
                    displayErrorMessage("Day is missing")
                }
                GraphicalInformationInputViewModel.GraphicalInformationInputError.MissingTime -> {
                    displayErrorMessage("Time is missing")
                }
            }
        })
    }

    private fun displayErrorMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
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

    private fun setupTimePicker(timePicker: TimePicker) {
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            viewModel.onTimeSelected(LocalTime.of(hourOfDay, minute))
        }
        viewModel.onTimeSelected(LocalTime.of(
            timePicker.hour,
            timePicker.minute
        ))
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

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.onDaySelected(resources.getStringArray(R.array.week_days)[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }
}