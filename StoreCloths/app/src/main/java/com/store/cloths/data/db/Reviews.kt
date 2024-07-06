package com.store.cloths.data.db

import com.store.cloths.data.db.entities.ReviewEntity

object Reviews {
    val testReview: ReviewEntity = ReviewEntity(
        clothId = 0,
        name = "Сергей Иванов",
        grade = 5f,
        id = 0,
        text = "Эта футболка мотивирует меня каждый день!",
    )
}