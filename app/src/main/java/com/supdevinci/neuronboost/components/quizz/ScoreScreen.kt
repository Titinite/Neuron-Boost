package com.supdevinci.neuronboost.components.quizz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.viewModel.QuizzViewModel

@Composable
fun ScoreScreen(viewModel: QuizzViewModel) {
    val score = viewModel.score.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Text(
                text = "Quiz terminé !",
                style = mainFont,
                color = AppColors.TextBlack
            )
            Text(
                text = "Ton score : $score / 20",
                style = mainFont,
                color = AppColors.TextBlack
            )
            Button(
                onClick = {
                    viewModel.fetchQuestions()  // Rejouer
                }
            ) {
                Text("Rejouer")
            }
        }
    }
}
