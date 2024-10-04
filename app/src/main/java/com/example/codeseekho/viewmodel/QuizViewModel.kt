package com.example.codeseekho.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.codeseekho.model.Question
import com.example.codeseekho.repository.QuizRepository

class QuizViewModel : ViewModel() {

    private val quizRepository = QuizRepository()
    private lateinit var questionsLiveData: LiveData<List<Question>>

    // Function to fetch questions
    fun fetchQuestions() {
        questionsLiveData = quizRepository.getQuestionsFromAPI()
    }

    // Getter for LiveData
    fun getQuestionsFromLiveData(): LiveData<List<Question>> {
        return questionsLiveData
    }
}
