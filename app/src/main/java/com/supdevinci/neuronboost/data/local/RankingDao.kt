package com.supdevinci.neuronboost.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.supdevinci.neuronboost.model.RankingEntity
import com.supdevinci.tasksapp.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface RankingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rank: RankingEntity): Long

    @Update
    fun update(rank: RankingEntity): Int

    @Query("UPDATE tasks SET deletedAt = :deletedAt WHERE id = :taskId")
    fun softDelete(taskId: Int, deletedAt: Date): Int

    @Query("SELECT * FROM tasks WHERE deletedAt IS NULL")
    fun getAllActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getById(taskId: Int): Flow<TaskEntity>
}