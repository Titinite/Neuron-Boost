package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme
import com.supdevinci.neuronboost.utils.decodeHtml
import com.supdevinci.neuronboost.viewModel.QuizzViewModel

class MainActivity : ComponentActivity() {

    private val quizzViewModel by viewModels<QuizzViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quizzViewModel.fetchQuestion()

        setContent {
            NeuronBoostTheme {
                QuizzScreen(quizzViewModel)
            }
        }
    }
}

@Composable
fun QuizzScreen(viewModel: QuizzViewModel) {
    val question by viewModel.responseData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        if (question != null) {
            Column {
                Text("Catégorie : ${question!!.category}")
                Text("Type : ${question!!.type}")
                Text("Question : ${question!!.question}")
                Text("Réponse correcte : ${question!!.correct_answer}")
                Text("Réponses incorrectes :")
                question!!.incorrect_answers.forEach {
                    Text("- $it")
                }
            }
        } else if (error != null) {
            Text("Erreur : $error")
        } else {
            Text("Chargement en cours...")
        }
    }
}