package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.supdevinci.neuronboost.components.ranking.RankingScreen
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme

class RankingActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuronBoostTheme {
                RankingScreen()
            }
        }
    }
}