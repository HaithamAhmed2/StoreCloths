package com.store.cloths.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.store.cloths.models.Cloth

@Entity(tableName = "cloths")
data class ClothEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val imageUrl: String,
    val author: String,
    val editionYear: Int,
    val description: String,
    val mark: Float,
    val price: Int,
) {
    fun toModel(): Cloth = Cloth(
        id, name, imageUrl, author, editionYear, description, mark, price, 0
    )
}