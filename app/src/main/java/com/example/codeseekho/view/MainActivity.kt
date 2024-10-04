package com.example.codeseekho.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codeseekho.LoginActivity
import com.example.codeseekho.R
import com.example.codeseekho.databinding.ActivityMainBinding
import com.example.codeseekho.model.Question
import com.example.codeseekho.viewmodel.QuizViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var quizViewModel: QuizViewModel
    private var questionsList: List<Question> = listOf()
    private var currentQuestionIndex = 0

    companion object {
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        enableEdgeToEdge()
        setContentView(view)

        // Resetting the scores
        result = 0
        totalQuestions = 0

        // Initialize the ViewModel
        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // Fetch questions from the API and observe changes
        quizViewModel.fetchQuestions() // Trigger API call

        // Observe the LiveData from the ViewModel
        quizViewModel.getQuestionsFromLiveData().observe(this, Observer { questions ->
            if (questions != null && questions.isNotEmpty()) {
                questionsList = questions
                totalQuestions = questionsList.size
                displayQuestion(currentQuestionIndex)
            } else {
                Log.e("TAGY", "No questions found or questions are null.")
            }
        })

        // Sign-out button functionality
        mainBinding.buttonSIgnOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Option click listeners
        mainBinding.radio1.setOnClickListener { checkAnswer(0) }
        mainBinding.radio2.setOnClickListener { checkAnswer(1) }
        mainBinding.radio3.setOnClickListener { checkAnswer(2) }
        mainBinding.radio4.setOnClickListener { checkAnswer(3) }

        // Next button functionality
        mainBinding.btnNext.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < totalQuestions) {
                displayQuestion(currentQuestionIndex)
            } else {
                Toast.makeText(this, "Quiz Finished! Score: $result/$totalQuestions", Toast.LENGTH_LONG).show()
                // Optionally, reset the quiz or navigate to another screen
            }
        }

        // Handle window insets for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun displayQuestion(index: Int) {
        val question = questionsList[index]
        mainBinding.apply {
            txtQuestion.text = question.question
            radio1.text = question.option1
            radio2.text = question.option2
            radio3.text = question.option3
            radio4.text = question.option4
            radio1.isChecked = false
            radio2.isChecked = false
            radio3.isChecked = false
            radio4.isChecked = false
        }
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val selectedAnswer = questionsList[currentQuestionIndex].getOptions()[selectedOptionIndex]
        val correctAnswer = questionsList[currentQuestionIndex].correctOption

        if (selectedAnswer == correctAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            result++ // Increment score
        } else {
            Toast.makeText(this, "Wrong! Correct answer is: $correctAnswer", Toast.LENGTH_SHORT).show()
        }
        updateScoreboard() // Update scoreboard after checking the answer
    }

    private fun updateScoreboard() {
        mainBinding.txtResult.text = "Score: $result/$totalQuestions"
    }

}
