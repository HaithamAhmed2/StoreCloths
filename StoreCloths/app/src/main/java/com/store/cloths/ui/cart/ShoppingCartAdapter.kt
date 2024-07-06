package com.store.cloths.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.store.cloths.R
import com.store.cloths.databinding.ItemShoppingCartClothBinding
import com.store.cloths.models.Cloth

class ShoppingCartAdapter(
    val onCountUpdate: (Long, Int) ->  Unit,
    val onCardClick: (Long) -> Unit,
) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    var list: List<Cloth> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        return ShoppingCartViewHolder(
            ItemShoppingCartClothBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShoppingCartViewHolder(val binding: ItemShoppingCartClothBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cloth: Cloth) {

            Glide
                .with(binding.root.context)
                .load(cloth.imageUrl)
                .into(binding.ivCloth)

            binding.llCounter.btnLeft.setOnClickListener {
                onCountUpdate(cloth.id, cloth.inCartCount-1)
            }

            binding.root.setOnClickListener {
                onCardClick(cloth.id)
            }

            binding.llCounter.btnRight.setOnClickListener {
                onCountUpdate(cloth.id, cloth.inCartCount+1)
            }

            binding.llCounter.tvCounter.text = cloth.inCartCount.toString()


            binding.tvName.text = cloth.name
            binding.tvAuthor.text = cloth.author
            binding.tvPrice.text = binding.root.context.getString(R.string.price, cloth.price)
        }
    }
}