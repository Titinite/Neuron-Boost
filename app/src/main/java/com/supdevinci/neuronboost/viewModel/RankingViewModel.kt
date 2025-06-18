package com.supdevinci.neuronboost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.neuronboost.data.local.RankingDatabase
import com.supdevinci.neuronboost.model.RankingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

class RankingViewModel(application: Application) : AndroidViewModel(application) {

    private val rankingDao = RankingDatabase.getDatabase(application).rankingDao()

    val rankings: StateFlow<List<RankingEntity>> = rankingDao.getAllActiveTasks()
        .map { it.sortedByDescending { rank -> rank.score } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertRanking(username: String, score: Int) {
        val rank = RankingEntity(
            id = 0,
            username = username,
            score = score,
            createdAt = Date(),
            updatedAt = null,
            deletedAt = null
        )

        viewModelScope.launch(Dispatchers.IO) {
            rankingDao.insert(rank)
        }
    }
}