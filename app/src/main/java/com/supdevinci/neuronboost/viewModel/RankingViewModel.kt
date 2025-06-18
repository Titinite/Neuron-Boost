package com.supdevinci.neuronboost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.neuronboost.data.local.RankingDatabase
import com.supdevinci.neuronboost.model.RankingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.text.insert

class RankingViewModel(application: Application) : AndroidViewModel(application) {

    private val rankingDao = RankingDatabase.getDatabase(application).rankingDao()

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
