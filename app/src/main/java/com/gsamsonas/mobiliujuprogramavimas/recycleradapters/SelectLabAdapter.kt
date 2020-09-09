package com.gsamsonas.mobiliujuprogramavimas.recycleradapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gsamsonas.mobiliujuprogramavimas.databinding.ItemSelectLabBinding

class SelectLabAdapter (
    private val labList: List<Pair<String, () -> Unit>>
) : RecyclerView.Adapter<SelectLabAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectLabBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return labList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (title, action) = labList[position]
        holder.bind(title, action)
    }

    inner class ViewHolder(private val binding: ItemSelectLabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String, action: () -> Unit) {
            binding.tvLabTitle.apply {
                text = title
                binding.tvLabTitle.setOnClickListener { action.invoke() }
            }
        }
    }
}