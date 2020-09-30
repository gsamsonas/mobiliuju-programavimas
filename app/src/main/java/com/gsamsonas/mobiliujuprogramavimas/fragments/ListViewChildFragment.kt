package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentListViewChildInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ListViewChildFragment private constructor(private val text: String) : Fragment() {

    private lateinit var binding: FragmentListViewChildInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListViewChildInfoBinding.inflate(inflater, container, false)
        binding.tvInfo.text = if (hasLetterA(text)) formatWithA(text) else formatWithoutA(text)
        return binding.root
    }

    private fun hasLetterA(text: String): Boolean {
        text.forEach { if (it == 'a' || it == 'A') return true }
        return false
    }

    private fun formatWithA(text: String): String {
        val size = text.decapitalize(Locale.getDefault()).filter { it == 'a' }.length
        return "Šiame tekste yra $size simboliai A(a)"
    }

    private fun formatWithoutA(text: String): String {
        val vowelCount = text.decapitalize(Locale.getDefault()).filter {
            it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u'
        }.length
        val capitalLetterCount = text.filter { it.isUpperCase() }.length
        val lowerCaseCount = text.filter { it.isLowerCase() }.length
        return """
            Balsės: $vowelCount
            Didžiosios raidės: $capitalLetterCount
            Mažosios raidės: $lowerCaseCount
        """.trimIndent()
    }

    companion object {
        fun get(text: String): ListViewChildFragment {
            return ListViewChildFragment(text)
        }
    }
}