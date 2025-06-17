package com.supdevinci.neuronboost.model

data class QuizzQuestion(
    val type: String,
    val category: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)