package com.store.cloths.data.db.relations

import androidx.room.Embedded
import com.store.cloths.data.db.entities.ClothEntity
import com.store.cloths.data.db.entities.ClothOrderCountEntity
import com.store.cloths.models.Cloth

data class OrderClothRelation(
    @Embedded
    val clothCount: ClothOrderCountEntity,

    @Embedded
    val cloth: ClothEntity
){
    fun toModel(): Cloth = Cloth(
        id = cloth.id,
        name = cloth.name,
        imageUrl = cloth.imageUrl,
        author = cloth.author,
        editionYear = cloth.editionYear,
        description = cloth.description,
        mark = cloth.mark,
        price = cloth.price,
        clothCount.count,
    )
}