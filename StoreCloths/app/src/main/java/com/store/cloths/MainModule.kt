package com.store.cloths

import androidx.room.Room
import com.store.cloths.data.ClothsRepository
import com.store.cloths.data.db.ClothsDatabase
import com.store.cloths.data.db.TypeConverters
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainModule(val app: App) {

    val database by lazy {
        ClothsDatabase.create(app)
    }

    val clothsDao by lazy {
        database.getClothsDao()
    }

    val executorService: ExecutorService by lazy { Executors.newCachedThreadPool() }

    val repository: ClothsRepository by lazy {
        ClothsRepository.Impl(
            clothsDao,
            database,
            executorService
        )
    }
}