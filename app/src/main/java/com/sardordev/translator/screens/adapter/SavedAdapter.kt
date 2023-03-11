package com.sardordev.translator.screens.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.translator.data.entity.SavedWords
import com.sardordev.translator.databinding.ItemCardSavedBinding

class SavedAdapter : ListAdapter<SavedWords, SavedAdapter.VH>(diff) {

    inner class VH(val binding: ItemCardSavedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(savedWords: SavedWords) {
            binding.fromtrans.text = savedWords.lanfrom
            binding.totrans.text = savedWords.lanto
            binding.tvfromword.text = savedWords.traslateW
            binding.towords.text = savedWords.translatedW
        }
    }


    object diff : DiffUtil.ItemCallback<SavedWords>() {
        override fun areItemsTheSame(oldItem: SavedWords, newItem: SavedWords): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SavedWords, newItem: SavedWords): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCardSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }


}