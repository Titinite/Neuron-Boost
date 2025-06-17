package com.supdevinci.neuronboost.data.local

import androidx.room.*
import com.supdevinci.neuronboost.model.RankingEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface RankingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rank: RankingEntity): Long

    @Update
    fun update(rank: RankingEntity): Int

    @Query("UPDATE ranking SET deletedAt = :deletedAt WHERE id = :rankId")
    fun softDelete(rankId: Int, deletedAt: Date): Int

    @Query("SELECT * FROM ranking WHERE deletedAt IS NULL")
    fun getAllActiveTasks(): Flow<List<RankingEntity>>

    @Query("SELECT * FROM ranking WHERE id = :rankId")
    fun getById(rankId: Int): Flow<RankingEntity>
}