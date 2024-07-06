package com.store.cloths.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@ProvidedTypeConverter
object TypeConverters {

    @TypeConverter
    fun convertDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun convertDate(time: Long): Date{
        return Date(time)
    }
}