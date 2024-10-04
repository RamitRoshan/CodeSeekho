package com.example.codeseekho.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codeseekho.model.Question
import com.example.codeseekho.model.QuestionsList
import com.example.codeseekho.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {

    private val questionsAPI = RetrofitInstance.getQuestionsAPI()

    // Method to fetch questions from the API
    fun getQuestionsFromAPI(): LiveData<List<Question>> {
        val data = MutableLiveData<List<Question>>()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val questionsList = questionsAPI.getQuestions()
                data.postValue(questionsList)
                Log.i("TAGY", "Data fetched: $questionsList")
            } catch (e: Exception) {
                Log.e("TAGY", "Exception: ${e.message}")
            }
        }
        return data
    }
}
