package com.store.cloths.ui.orders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.store.cloths.R
import com.store.cloths.databinding.ItemOrderDetailBinding
import com.store.cloths.models.Cloth

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var list: List<Cloth> = emptyList()
        set(value) {
            Log.e("DEBUGG", value.size.toString())
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemOrderDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OrderViewHolder(val binding: ItemOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cloth: Cloth) {
            binding.tvName.text = cloth.name
            binding.tvCount.text =
                binding.root.context.getString(R.string.quantity, cloth.inCartCount)
        }
    }
}