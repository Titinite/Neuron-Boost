package com.supdevinci.neuronboost.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.neuronboost.data.RetrofitInstance
import com.supdevinci.neuronboost.model.QuizzQuestion
import com.supdevinci.neuronboost.service.Quizz
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizzViewModel : ViewModel() {

    private val api: Quizz = RetrofitInstance.api.create(Quizz::class.java)

    private val _responseData = MutableStateFlow<QuizzQuestion?>(null)
    val responseData = _responseData.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun fetchQuestion() {
        viewModelScope.launch {
            try {
                val response = api.getOneQuestion()
                if (response.isSuccessful) {
                    val data = response.body()?.results?.firstOrNull()
                    if (data != null) {
                        _responseData.value = data
                        _errorMessage.value = null
                    } else {
                        _errorMessage.value = "Aucune question reçue."
                    }
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
}
