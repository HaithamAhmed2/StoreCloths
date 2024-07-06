package com.store.cloths.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.store.cloths.R
import com.store.cloths.databinding.ItemCatalogClothBinding
import com.store.cloths.models.Cloth

class CatalogAdapter(
    val onCardClick: (Long) -> Unit,
    val onClothBuy: (Long) -> Unit,
    val onClothDelete: (Long) -> Unit,
    val onClothAdd: (Long) -> Unit,
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    var list: List<Cloth> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return CatalogViewHolder(
            ItemCatalogClothBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CatalogViewHolder(val binding: ItemCatalogClothBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cloth: Cloth) {

            Glide
                .with(binding.root.context)
                .load(cloth.imageUrl)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onCardClick(cloth.id)
            }

            binding.btnBuy.setOnClickListener {
                onClothBuy(cloth.id)
            }

            binding.tvName.text = cloth.name
            binding.tvPrice.text = binding.root.context.getString(R.string.price, cloth.price)
            binding.tvAuthor.text = cloth.author

            if (cloth.inCartCount != 0) {
                binding.btnBuy.visibility = View.GONE
                binding.btnCounter.root.visibility = View.VISIBLE

                binding.btnCounter.tvCounter.text = cloth.inCartCount.toString()

                binding.btnCounter.btnLeft.setOnClickListener {
                    onClothDelete(cloth.id)
                }

                binding.btnCounter.btnRight.setOnClickListener {
                    onClothAdd(cloth.id)
                }
            } else {
                binding.btnBuy.visibility = View.VISIBLE
                binding.btnCounter.root.visibility = View.GONE

            }
        }
    }
}