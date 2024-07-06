package com.store.cloths.ui.product

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.store.cloths.databinding.ItemReviewBinding
import com.store.cloths.models.Review

class ProductPageAdapter : RecyclerView.Adapter<ProductPageAdapter.ProductPageViewHolder>() {

    var list: List<Review> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductPageViewHolder {
        return ProductPageViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductPageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ProductPageViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            binding.tvName.text = review.name
            binding.tvGrade.text = review.grade.toString()
            binding.tvText.text = review.text
        }
    }
}