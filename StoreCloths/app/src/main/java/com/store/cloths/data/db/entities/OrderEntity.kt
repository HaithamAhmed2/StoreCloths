package com.store.cloths.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.store.cloths.models.Order
import java.util.Date

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: Date,
    val quantityCloths : Int,
    val totalPrice: Int
){
    fun toModel(): Order = Order(
        id = id,
        date = date,
        number = id.toString(),
        quantityCloths = quantityCloths,
        totalPrice = totalPrice
    )
}
