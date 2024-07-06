package com.store.cloths.ui.catalog

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.store.cloths.data.ClothsRepository
import com.store.cloths.models.Cloth

class CatalogViewModel(private val repository: com.store.cloths.data.ClothsRepository) : ViewModel() {
    private val cloths = repository.getCatalogCloths()
    private val searchQuery = MutableLiveData("")

    val catalog = MediatorLiveData<List<Cloth>>()

    private val catalogObserver = Observer<List<Cloth>> {

    }

    init {
        catalog.addSource(cloths) {
            catalog.value = filterCloths(it, searchQuery.value ?: "")
        }
        catalog.addSource(searchQuery) {
            catalog.value = filterCloths(cloths.value ?: emptyList(), it)
        }
    }

    fun applySearch(query: String) {
        searchQuery.value = query
    }

    fun addToCart(clothId: Long) {
        repository.addToCart(clothId, 1)
    }

    fun increaseInCart(clothId: Long) {
        cloths.value?.single { it.id == clothId }
        repository.addToCart(
            clothId,
            (cloths.value?.single { it.id == clothId }?.inCartCount ?: 0) + 1
        )
    }

    fun decreaseInCart(clothId: Long) {
        cloths.value?.single { it.id == clothId }
        repository.addToCart(
            clothId,
            (cloths.value?.single { it.id == clothId }?.inCartCount ?: 0) - 1
        )
    }

    private fun filterCloths(list: List<Cloth>, query: String): List<Cloth> {
        return if (query.isNotBlank()) {
            list.filter {
                it.name.contains(query) || it.author.contains(query)
            }
        } else list
    }

    override fun onCleared() {
        super.onCleared()
        cloths.removeObserver(catalogObserver)
    }

    class Factory(val repository: com.store.cloths.data.ClothsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CatalogViewModel(repository) as T
        }
    }
}