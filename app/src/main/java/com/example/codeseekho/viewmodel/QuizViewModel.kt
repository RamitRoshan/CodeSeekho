package com.example.codeseekho.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.codeseekho.model.QuestionsList
import com.example.codeseekho.repository.QuizRepository

class QuizViewModel : ViewModel() {

    var repository: QuizRepository = QuizRepository()
    lateinit var questionsLiveData: LiveData<QuestionsList>


    init {
        questionsLiveData = repository.getQuestionsFromAPI()
    }

    fun getQuestionsFromLiveData():LiveData<QuestionsList>{
        return questionsLiveData
    }



}