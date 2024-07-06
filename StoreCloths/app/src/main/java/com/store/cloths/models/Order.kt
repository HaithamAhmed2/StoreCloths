package com.store.cloths.models

import java.util.Date

data class Order(
    val id: Long,
    val date: Date,
    val number: String,
    val quantityCloths : Int,
    val totalPrice: Int,
)
