package com.store.cloths.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.store.cloths.R
import com.store.cloths.databinding.FragmentShoppingCartBinding
import com.store.cloths.ui.app
import com.store.cloths.ui.orders.OrderFragment
import com.store.cloths.ui.product.ProductPageFragment

class ShoppingCartFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartBinding

    val viewModel by lazy {
        val factory = ShoppingCartViewModel.Factory(app.mainModule.repository)
        ViewModelProvider(this, factory).get(ShoppingCartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shoppingCartAdapter = ShoppingCartAdapter(
            onCountUpdate = { clothId, count ->
            if (count in 0..50) {
                app.mainModule.repository.addToCart(clothId, count)
            }
        },
        onCardClick = {
            findNavController().navigate(R.id.action_shopping_cart_to_product_page, ProductPageFragment.createBundle(it))
        })

        val gridLayout = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.rvShoppingCart.apply {
            adapter = shoppingCartAdapter
            layoutManager = gridLayout
        }

        viewModel.cart.observe(viewLifecycleOwner) {
            shoppingCartAdapter.list = it
        }

        binding.btnGoBack.setOnClickListener {
            Log.d(  "test", "btnGoBack")
        }

        binding.btnOrdering.setOnClickListener {
            viewModel.makeOrder()?.observe(viewLifecycleOwner){
                findNavController().navigate(
                    R.id.action_shopping_cart_to_orderFragment,
                    OrderFragment.createBundle(it)
                )
            }
        }
    }
}