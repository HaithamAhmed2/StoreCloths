package com.store.cloths.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.store.cloths.data.db.entities.ClothCartCountEntity
import com.store.cloths.data.db.entities.ClothEntity
import com.store.cloths.data.db.entities.ClothOrderCountEntity
import com.store.cloths.data.db.entities.OrderEntity
import com.store.cloths.data.db.entities.ReviewEntity

@Database(
    entities = [
        ClothEntity::class, ClothOrderCountEntity::class, ClothCartCountEntity::class, OrderEntity::class,
        ReviewEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.store.cloths.data.db.TypeConverters::class)
abstract class ClothsDatabase :  RoomDatabase(){

    abstract fun getClothsDao(): ClothsDAO

    companion object{
        fun create(context: Context): ClothsDatabase = Room
            .databaseBuilder(context, ClothsDatabase::class.java, "clothsDB")
            .createFromAsset("cloths.db")
            .build()
    }
}