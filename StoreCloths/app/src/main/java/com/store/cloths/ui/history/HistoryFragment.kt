package com.store.cloths.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.store.cloths.R
import com.store.cloths.databinding.FragmentHistoryBinding
import com.store.cloths.ui.app
import com.store.cloths.ui.orders.OrderFragment

class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = HistoryAdapter(
            onCardClick = {
                findNavController().navigate(
                    R.id.action_history_to_orderFragment,
                    OrderFragment.createBundle(it)
                )
            },
        )
        val gridLayout = LinearLayoutManager(
            context
        )
        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = gridLayout
        }
        app.mainModule.repository.getHistory().observe(viewLifecycleOwner) {
            historyAdapter.list = it
        }
    }
}