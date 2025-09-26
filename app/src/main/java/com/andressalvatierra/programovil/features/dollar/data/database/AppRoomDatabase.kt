package com.andressalvatierra.programovil.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andressalvatierra.programovil.features.dollar.data.database.dao.IDollarDao
import com.andressalvatierra.programovil.features.dollar.data.database.entity.DollarEntity
import com.andressalvatierra.programovil.features.movie.data.database.dao.IMovieDao
import com.andressalvatierra.programovil.features.movie.data.database.entity.MovieEntity

@Database(entities = [DollarEntity::class, MovieEntity::class], version = 2, exportSchema = false)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun dollarDao(): IDollarDao
    abstract fun movieDao(): IMovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "ucbp1_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}