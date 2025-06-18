package com.supdevinci.neuronboost.viewModel

import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.neuronboost.data.RetrofitInstance
import com.supdevinci.neuronboost.model.AnswerOptions
import com.supdevinci.neuronboost.model.QuizzQuestion
import com.supdevinci.neuronboost.service.Quizz
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuizzViewModel : ViewModel() {

    private val api: Quizz = RetrofitInstance.api.create(Quizz::class.java)

    private val _allQuestions = MutableStateFlow<List<QuizzQuestion>>(emptyList())
    val allQuestions = _allQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex = _currentQuestionIndex.asStateFlow()

    val currentQuestion: StateFlow<QuizzQuestion?> =
        _allQuestions.combine(_currentQuestionIndex) { list, index ->
            list.getOrNull(index)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _shuffledAnswers = MutableStateFlow<List<AnswerOptions>>(emptyList())
    val shuffledAnswers = _shuffledAnswers.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _selectedAnswer = MutableStateFlow<AnswerOptions?>(null)
    val selectedAnswer = _selectedAnswer.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    private val _questionCount = MutableStateFlow(0)
    val questionCount = _questionCount.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished = _isQuizFinished.asStateFlow()

    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val response = api.getQuestions()
                if (response.isSuccessful) {
                    val data = response.body()?.results ?: emptyList()
                    _allQuestions.value = data
                    _currentQuestionIndex.value = 0
                    _selectedAnswer.value = null
                    _errorMessage.value = null
                    prepareAnswers()
                } else {
                    _errorMessage.value = "Erreur de l'API : ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = when (e) {
                    is java.net.UnknownHostException -> "Pas de connexion Internet"
                    is java.net.SocketTimeoutException -> "Le serveur ne répond pas"
                    else -> "Erreur inattendue : ${e.message}"
                }
            }
        }
    }

    private fun prepareAnswers() {
        val question = currentQuestion.value
        if (question != null) {
            val allAnswers = (question.incorrect_answers.map { AnswerOptions(it, false) } +
                    AnswerOptions(question.correct_answer, true)).shuffled()
            _shuffledAnswers.value = allAnswers
        }
    }

    fun selectAnswer(answer: AnswerOptions) {
        _selectedAnswer.value = answer
        if (answer.isCorrect) {
            _score.value += 1
        }
        viewModelScope.launch {
            delay(1000)
            nextQuestion()
        }
    }

    private fun nextQuestion() {
        if (_currentQuestionIndex.value < _allQuestions.value.lastIndex) {
            _currentQuestionIndex.value += 1
            _selectedAnswer.value = null
            prepareAnswers()
        } else {
            _isQuizFinished.value = true
        }
    }

    fun decodeHtml(text: String): String {
        return HtmlCompat.fromHtml(text,HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}
