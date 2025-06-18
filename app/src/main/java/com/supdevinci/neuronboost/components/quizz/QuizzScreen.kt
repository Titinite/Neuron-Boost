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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.labelCategory
import com.supdevinci.neuronboost.ui.theme.labelQuestionNumber
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.utils.decodeHtml
import com.supdevinci.neuronboost.viewModel.QuizzViewModel
import com.supdevinci.neuronboost.viewModel.RankingViewModel
import com.supdevinci.neuronboost.viewModel.UserViewModel
import com.supdevinci.neuronboost.viewModel.ScoreViewModel

@Composable
fun QuizzScreen(
    quizzViewModel: QuizzViewModel,
    scoreViewModel: ScoreViewModel,
    userViewModel: UserViewModel,
    rankingViewModel: RankingViewModel
) {
    val question by quizzViewModel.currentQuestion.collectAsState()
    val answers by quizzViewModel.shuffledAnswers.collectAsState()
    val selectedAnswer by quizzViewModel.selectedAnswer.collectAsState()
    val error by quizzViewModel.errorMessage.collectAsState()

    val currentIndex by quizzViewModel.currentQuestionIndex.collectAsState()
    val progress = (currentIndex).toFloat() / quizzViewModel.totalQuestions
    val isFinished by quizzViewModel.isQuizFinished.collectAsState()

    LaunchedEffect(Unit) {
        quizzViewModel.fetchQuestions()
    }

    if (isFinished) {
        ScoreScreen(quizzViewModel, scoreViewModel, userViewModel, rankingViewModel)
    } else {
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
                        androidx.compose.material3.LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = AppColors.QuizzGreen,
                            trackColor = AppColors.GreenBarEmpty
                        )

                        Text(
                            text = "Question ${currentIndex + 1} sur ${quizzViewModel.totalQuestions}",
                            style = labelQuestionNumber,
                            color = AppColors.TextColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

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
                                    question!!.category.decodeHtml(),
                                    style = labelCategory,
                                    color = AppColors.TextBlack,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    question!!.question.decodeHtml(),
                                    style = mainFont,
                                    color = AppColors.TextBlack,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        answers.forEach { answer ->
                            Button(
                                onClick = {
                                    if (selectedAnswer == null) quizzViewModel.selectAnswer(answer)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.QuizzResponsePropositions,
                                    contentColor = AppColors.TextBlack,
                                    disabledContainerColor = AppColors.QuizzResponsePropositions,
                                    disabledContentColor = AppColors.TextBlack
                                ),
                                shape = RoundedCornerShape(16.dp),
                                contentPadding = PaddingValues(vertical = 20.dp),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = selectedAnswer == null
                            ) {
                                Text(
                                    answer.text.decodeHtml(),
                                    style = mainFont,
                                    color = AppColors.TextBlack,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                } else if (error != null) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Erreur", style = mainFont, color = AppColors.TextColor)
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Chargement en cours...", style = mainFont, color = AppColors.TextColor
                        )
                    }
                }
            }
        }
    }
}