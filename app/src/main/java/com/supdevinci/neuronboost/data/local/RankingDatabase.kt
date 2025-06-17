package com.supdevinci.neuronboost.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.supdevinci.neuronboost.model.RankingEntity
import com.supdevinci.neuronboost.data.local.RankingDao

@Database(entities = [RankingEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class RankingDatabase : RoomDatabase() {

    abstract fun rankingDao(): RankingDao

    companion object {
        @Volatile
        private var INSTANCE: RankingDatabase? = null

        fun getDatabase(context: Context): RankingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RankingDatabase::class.java,
                    "ranking_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}