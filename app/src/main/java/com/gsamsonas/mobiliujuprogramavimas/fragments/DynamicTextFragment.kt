package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentDynamicTextBinding
import com.gsamsonas.mobiliujuprogramavimas.features.externalinput.EXTERNAL_INPUT_REQUEST_CODE
import com.gsamsonas.mobiliujuprogramavimas.features.externalinput.EXTERNAL_INPUT_RETURN_CODE
import com.gsamsonas.mobiliujuprogramavimas.features.externalinput.ExternalInputActivity
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.DynamicTextViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DynamicTextFragment : Fragment() {

    private val viewModel: DynamicTextViewModel by viewModels()

    lateinit var binding: FragmentDynamicTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDynamicTextBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.message.observe(viewLifecycleOwner) {
            if (it != null) binding.tvTotalWords.visibility = View.VISIBLE
        }

        viewModel.externalMessage.observe(viewLifecycleOwner) {
            binding.bExternalInput.isEnabled = it.isNullOrBlank()
            binding.tvExternalMessage.visibility = if(it.isNullOrBlank()) View.GONE else View.VISIBLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bExternalInput.setOnClickListener {
            startActivityForResult(
                ExternalInputActivity.getIntent(requireContext()),
                EXTERNAL_INPUT_REQUEST_CODE
            )
        }

        binding.tvExternalMessage.setOnClickListener {
            findNavController().navigate(
                DynamicTextFragmentDirections.actionDynamicTextFragmentToStaticTextAnalysisFragment(
                    viewModel.getExternalMessage()
                )
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EXTERNAL_INPUT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val input = data?.extras?.getString(EXTERNAL_INPUT_RETURN_CODE)
                viewModel.onReceivedExternalMessage(input)
            }
        }
    }
}