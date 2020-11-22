package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentMenuAndThreadsBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.MenuAndThreadsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalTime

@AndroidEntryPoint
class MenuAndThreadsFragment : Fragment() {

    private val viewModel: MenuAndThreadsViewModel by viewModels()

    private lateinit var binding: FragmentMenuAndThreadsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSelectTime -> {
                openTimePickerDialog()
                true
            }
            R.id.menuFinish -> {
                requireActivity().finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openTimePickerDialog() {
        val currentTime = LocalTime.now()
        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                viewModel.onTimeSelected(LocalTime.of(hourOfDay, minute))
            },
            currentTime.hour,
            currentTime.minute,
            false
        ).show()
    }
}