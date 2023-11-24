package com.hellcorp.restquest.ui.room.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.ItemRoomBinding
import com.hellcorp.restquest.domain.room.Room
import com.hellcorp.restquest.ui.hotel.adapters.ImagePagerAdapter
import com.hellcorp.restquest.ui.hotel.adapters.PeculiaritiesAdapter
import java.text.NumberFormat
import java.util.Locale

class RoomsAdapter(private val rooms: List<Room>) :
    RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoomBinding.inflate(inflater, parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(rooms[position])
    }

    override fun getItemCount(): Int = rooms.size

    inner class RoomViewHolder(private val binding: ItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) = with(binding) {
            val peculiaritiesAdapter = PeculiaritiesAdapter(room.peculiarities)
            val imagePagerAdapter = ImagePagerAdapter(room.imageList)
            val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            val formattedPrice = room.price.let { numberFormat.format(it) } ?: "N/A"
            val price = itemView.context.getString(R.string.room_price, formattedPrice)
            viewpager.adapter = imagePagerAdapter
            indicator.setViewPager(binding.viewpager)
            roomType.text = room.name
            recyclerView.apply {
                adapter = peculiaritiesAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
            tvPrice.text = price
            tvPriceCondition.text = room.priceConditions
        }
    }
}