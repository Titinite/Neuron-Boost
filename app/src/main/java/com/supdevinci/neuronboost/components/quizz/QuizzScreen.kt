package com.supdevinci.neuronboost.components.quizz

import android.content.Intent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.R
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.buttonMenu
import com.supdevinci.neuronboost.ui.theme.labelCategory
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.view.QuizzActivity
import com.supdevinci.neuronboost.viewModel.QuizzViewModel

@Composable
fun QuizzScreen(viewModel: QuizzViewModel) {
    val question by viewModel.responseData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    //Text("Type : ${question!!.type}")
    //Text("Réponse correcte : ${question!!.correct_answer}")
    //Text("Réponses incorrectes :")
    //question!!.incorrect_answers.forEach {
    //    Text("- $it")

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

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.TextColor),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            question!!.category,
                            style = labelCategory,
                            color = AppColors.TextBlack
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(question!!.question, style = mainFont, color = AppColors.TextBlack)
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