package com.supdevinci.neuronboost.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ranking")
data class RankingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var username: String,
    var score: Int,
    var createdAt: Date,
    var updatedAt: Date,
    var deletedAt: Date,
)