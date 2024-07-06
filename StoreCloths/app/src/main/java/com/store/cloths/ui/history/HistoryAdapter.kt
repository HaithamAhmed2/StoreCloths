package com.store.cloths.ui.history

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.store.cloths.R
import com.store.cloths.databinding.ItemHistoryBinding
import com.store.cloths.models.Order

class HistoryAdapter(
    val onCardClick: (Long) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var list: List<Order> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HistoryViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {

            val formatter = SimpleDateFormat ("dd.MM.yy")
            binding.tvDate.text = binding.root.context.getString(
                R.string.date, formatter.format(order.date)
            )
            binding.tvNumber.text = binding.root.context.getString(R.string.number, order.number)
            binding.tvQuantity.text =
                binding.root.context.getString(R.string.quantity, order.quantityCloths)
            binding.tvTotalPrice.text =
                binding.root.context.getString(R.string.price, order.totalPrice)

            binding.root.setOnClickListener {
                onCardClick(order.id)
            }
        }
    }

}