package com.supdevinci.neuronboost.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.supdevinci.neuronboost.ui.theme.AppColors

class ScoreViewModel: ViewModel() {
    fun getScoreColor(score: Int): Color {
        return when {
            score > 15 -> AppColors.ScoreGreat
            score > 10 -> AppColors.ScoreGood
            score > 5 -> AppColors.ScoreBad
            else -> AppColors.ScoreNoob
        }
    }

    fun getScoreLabel(score: Int): String {
        return when {
            score > 15 -> "Excellent !"
            score > 10 -> "Bien joué !"
            score > 5 -> "Peu mieux faire."
            else -> "Catastrophique..."
        }
    }
}