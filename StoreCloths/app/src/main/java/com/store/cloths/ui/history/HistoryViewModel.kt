package com.store.cloths.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.store.cloths.models.Order
import java.util.Date

class HistoryViewModel: ViewModel() {
    private val testOrder: List<Order> = listOf(
        Order(
            id = 0,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 1,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 2,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 3,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 4,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 5,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
        Order(
            id = 6,
            date = Date(),
            number = "192-123-12300-1",
            quantityCloths = 15,
            totalPrice = 15000
        ),
    )

    private val _history = MutableLiveData(testOrder)
    val history: LiveData<List<Order>> = _history

}