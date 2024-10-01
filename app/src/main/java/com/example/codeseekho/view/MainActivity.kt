package com.example.codeseekho.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codeseekho.LoginActivity
import com.example.codeseekho.R
import com.example.codeseekho.databinding.ActivityMainBinding
import com.example.codeseekho.model.Question
import com.example.codeseekho.model.QuestionsList
import com.example.codeseekho.viewmodel.QuizViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {




    lateinit var mainBinding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionsList: List<Question>

    companion object{
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Resetting the scores
        result = 0
        totalQuestions = 0

        //Getting the response
        quizViewModel = ViewModelProvider(this)
            .get(QuizViewModel::class.java)


        //Displaying the First Question
        GlobalScope.launch (Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {

                if(it.size>0){
                    questionsList = it
                    Log.i("TAGY","This the 1st question: ${questionsList[0]}")

                    mainBinding.apply {
                        txtQuestion.text = questionsList!![0].question
                        radio1.text = questionsList!![0].option1
                        radio2.text = questionsList!![0].option2
                        radio3.text = questionsList!![0].option3
                        radio4.text = questionsList!![0].option4
                    }
                }


            })
        }


        enableEdgeToEdge()

        setContentView(view)




        mainBinding.buttonSIgnOut.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}