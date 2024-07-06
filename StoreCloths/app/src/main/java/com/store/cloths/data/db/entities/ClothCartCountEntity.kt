package com.store.cloths.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shopping_cart")
data class ClothCartCountEntity(
    @PrimaryKey(autoGenerate = true) val clothId: Long,
    val count: Int,
)