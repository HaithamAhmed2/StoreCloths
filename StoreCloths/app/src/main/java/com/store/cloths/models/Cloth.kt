package com.store.cloths.models

import com.store.cloths.data.db.entities.ClothEntity

data class Cloth(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val author: String,
    val editionYear: Int,
    val description: String,
    val mark: Float,
    val price: Int,
    val inCartCount: Int = 0,
) {

    fun toEntity() = ClothEntity(
        id, name, imageUrl, author, editionYear, description, mark, price
    )
}