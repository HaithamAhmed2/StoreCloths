package com.store.cloths.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.store.cloths.data.ClothsRepository

class ShoppingCartViewModel (val repository: com.store.cloths.data.ClothsRepository) : ViewModel(){

    val cart = repository.getCart()

    fun makeOrder(): LiveData<Long>?{
        val cartCloths = cart.value
        if(cartCloths != null && !cartCloths.isEmpty()){
            return repository.makeOrder(cartCloths.map { cloth -> cloth.id to cloth.inCartCount })
        }else{
            return null
        }
    }

    class Factory(val repository: com.store.cloths.data.ClothsRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingCartViewModel(repository) as T
        }
    }
}