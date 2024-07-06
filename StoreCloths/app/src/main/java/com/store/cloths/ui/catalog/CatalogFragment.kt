package com.store.cloths.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.store.cloths.R
import com.store.cloths.databinding.FragmentCatalogBinding
import com.store.cloths.ui.app
import com.store.cloths.ui.hideKeyboard
import com.store.cloths.ui.product.ProductPageFragment

class CatalogFragment : Fragment() {

    lateinit var binding: FragmentCatalogBinding

    val viewModel by lazy {
        val factory = CatalogViewModel.Factory(app.mainModule.repository)
        ViewModelProvider(this, factory)[CatalogViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogAdapter = CatalogAdapter(
            onCardClick = {
                findNavController().navigate(
                    R.id.action_catalog_to_productPageFragment,
                    ProductPageFragment.createBundle(it)
                )
            },
            onClothBuy = {
                viewModel.addToCart(it)
            },
            onClothDelete = {
                viewModel.decreaseInCart(it)
            },
            onClothAdd = {
                viewModel.increaseInCart(it)
            }
        )


        val gridLayout = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.tvSearch.setOnEditorActionListener { v, actionId, event ->
            binding.tvSearch.hideKeyboard()
            true
        }

        binding.tvSearch.addTextChangedListener { text ->
            viewModel.applySearch(text.toString())
        }


        binding.rvCatalog.apply {
            adapter = catalogAdapter
            layoutManager = gridLayout
        }

        viewModel.catalog.observe(viewLifecycleOwner) {
            catalogAdapter.list = it
        }
    }
}