package com.supdevinci.neuronboost.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.supdevinci.neuronboost.components.menu.HomeScreen
import com.supdevinci.neuronboost.ui.theme.NeuronBoostTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuronBoostTheme {
                HomeScreen()
            }
        }
    }
}