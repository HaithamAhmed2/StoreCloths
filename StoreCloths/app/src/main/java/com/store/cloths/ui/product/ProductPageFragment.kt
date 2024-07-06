package com.store.cloths.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.store.cloths.R
import com.store.cloths.databinding.FragmentProductPageBinding
import com.store.cloths.ui.app

class ProductPageFragment : Fragment() {

    lateinit var binding: FragmentProductPageBinding

    val clothId by lazy { arguments?.getLong(ID, 0L) ?: 0L }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductPageBinding.bind(view)


        val productPageAdapter = ProductPageAdapter()

        val gridLayout = LinearLayoutManager(
            context
        )

        binding.rvProductPage.apply {
            adapter = productPageAdapter
            layoutManager = gridLayout
        }

        app.mainModule.repository.getClothReviews(clothId).observe(viewLifecycleOwner) {
            productPageAdapter.list = it
        }

        binding.btnGoBack.setOnClickListener { findNavController().navigate(R.id.action_productPageFragment_to_catalog) }

        app.mainModule.repository.getClothDetails(clothId).observe(viewLifecycleOwner) {
            val cloth = it
            binding.run {
                tvAuthor.text = it.author
                tvAnnotation.text = it.description
                tvName.text = it.name
                tvEdition.text = it.editionYear.toString()
                tvGrade.text = it.mark.toString()
                tvPrice.text = it.price.toString()
                Glide
                    .with(view)
                    .load(it.imageUrl)
                    .into(ivCloth)

                binding.llCounter.btnRight.setOnClickListener {
                    app.mainModule.repository.addToCart(cloth.id, cloth.inCartCount + 1)
                }

                llCounter.btnLeft.setOnClickListener {
                    app.mainModule.repository.addToCart(clothId, cloth.inCartCount - 1)
                }

                btnBuy.setOnClickListener {
                    app.mainModule.repository.addToCart(clothId, cloth.inCartCount + 1)
                }

                if (cloth.inCartCount != 0) {
                    btnBuy.visibility = View.GONE
                    llCounter.tvCounter.text = cloth.inCartCount.toString()
                    llCounter.root.visibility = View.VISIBLE
                } else {
                    btnBuy.visibility = View.VISIBLE
                    llCounter.root.visibility = View.GONE
                }
            }
        }

    }

    companion object {
        private const val ID = "ID"

        fun createBundle(id: Long): Bundle {
            val bundle = Bundle()
            bundle.putLong(ID, id)
            return bundle
        }

    }

}