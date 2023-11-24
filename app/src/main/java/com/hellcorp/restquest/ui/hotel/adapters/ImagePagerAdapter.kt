package com.hellcorp.restquest.ui.hotel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.ItemImageBinding

class ImagePagerAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImagePagerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .centerCrop()
                .transform(RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.dimen_16dp)))
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size
}
