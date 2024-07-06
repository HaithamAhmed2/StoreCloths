package com.store.cloths.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.store.cloths.data.db.entities.ClothCartCountEntity
import com.store.cloths.data.db.entities.ClothEntity
import com.store.cloths.models.Cloth

data class ClothCartCountRelation(
    @Embedded
    val cloth: ClothEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "clothId"
    )
    val count: ClothCartCountEntity?
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
        inCartCount = count?.count ?: 0
    )
}
