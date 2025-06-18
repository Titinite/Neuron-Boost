package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.supdevinci.neuronboost.components.quizz.QuizzScreen
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme
import com.supdevinci.neuronboost.viewModel.QuizzViewModel

class QuizzActivity : ComponentActivity() {

    private val quizzViewModel by viewModels<QuizzViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuronBoostTheme {
                QuizzScreen(quizzViewModel)
            }
        }
    }
}