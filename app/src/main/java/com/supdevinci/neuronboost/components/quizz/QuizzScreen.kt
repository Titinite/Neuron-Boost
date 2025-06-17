package com.supdevinci.neuronboost.components.quizz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.labelCategory
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.utils.decodeHtml
import com.supdevinci.neuronboost.viewModel.QuizzViewModel
import androidx.compose.runtime.*
import com.supdevinci.neuronboost.model.AnswerOptions

@Composable
fun QuizzScreen(viewModel: QuizzViewModel) {
    val question by viewModel.responseData.collectAsState()
    val answers by viewModel.shuffledAnswers.collectAsState()
    var selectedAnswer by remember { mutableStateOf<AnswerOptions?>(null) }
    val error by viewModel.errorMessage.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 32.dp)
        ) {

            if (question != null) {

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier.width(400.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(275.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(AppColors.TextColor)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                        ) {
                            Text(
                                decodeHtml(question!!.category),
                                style = labelCategory,
                                color = AppColors.TextBlack
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                decodeHtml(question!!.question),
                                style = mainFont,
                                color = AppColors.TextBlack,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    answers.forEach { answer ->
                        val isSelected = selectedAnswer != null
                        val isCorrect = answer.isCorrect
                        val backgroundColor = when {
                            !isSelected -> AppColors.QuizzResponsePropositions
                            answer == selectedAnswer && isCorrect -> AppColors.QuizzGreen
                            answer == selectedAnswer && !isCorrect -> AppColors.QuizzResponseWrong
                            else -> AppColors.QuizzResponsePropositions
                        }
                        Button(
                            onClick = {
                                if (selectedAnswer == null) {
                                    selectedAnswer = answer
                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(vertical = 20.dp),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = selectedAnswer == null
                        ) {
                            Text(
                                decodeHtml(answer.text),
                                style = mainFont,
                                color = AppColors.TextBlack
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

            } else if (error != null) {
                Text("Erreur")
            } else {
                Text("Chargement en cours...")
            }
        }
    }
}