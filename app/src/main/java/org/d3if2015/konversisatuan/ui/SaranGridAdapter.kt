package org.d3if2015.konversisatuan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2015.konversisatuan.databinding.FragmentAboutGridBinding
import org.d3if2015.konversisatuan.network.Biodata

class SaranGridAdapter: ListAdapter<Biodata, SaranGridAdapter.MarsPhotoViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(FragmentAboutGridBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    class MarsPhotoViewHolder(private var binding: FragmentAboutGridBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(Picture: Biodata){
            binding.photo = Picture
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Biodata>(){
        override fun areItemsTheSame(oldItem: Biodata, newItem: Biodata): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Biodata, newItem: Biodata): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }
}
