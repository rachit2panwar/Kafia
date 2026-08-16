package com.coffeeshop.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coffeeshop.core.data.db.entity.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
