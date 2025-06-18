package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.supdevinci.neuronboost.components.quizz.QuizzScreen
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme
import com.supdevinci.neuronboost.viewModel.QuizzViewModel
import com.supdevinci.neuronboost.viewModel.RankingViewModel
import com.supdevinci.neuronboost.viewModel.ScoreViewModel
import com.supdevinci.neuronboost.viewModel.UserViewModel

class QuizzActivity : ComponentActivity() {

    private val quizzViewModel by viewModels<QuizzViewModel>()
    private val scoreViewModel by viewModels<ScoreViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val rankingViewModel by viewModels<RankingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuronBoostTheme {
                QuizzScreen(quizzViewModel, scoreViewModel, userViewModel, rankingViewModel)
            }
        }
    }
}