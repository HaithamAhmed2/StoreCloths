package com.store.cloths.data.db.entities

import androidx.room.Entity

@Entity(tableName = "order_cloths_count", primaryKeys = ["orderId", "clothId"])
data class ClothOrderCountEntity(
    val orderId: Long,
    val clothId: Long,
    val count: Int,
)
