package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.supdevinci.neuronboost.components.ranking.RankingScreen
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme
import com.supdevinci.neuronboost.viewModel.RankingViewModel
import com.supdevinci.neuronboost.viewModel.UserViewModel
import kotlin.getValue

class RankingActivity: ComponentActivity() {

    private val rankingViewModel by viewModels<RankingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuronBoostTheme {
                RankingScreen(rankingViewModel)
            }
        }
    }
}