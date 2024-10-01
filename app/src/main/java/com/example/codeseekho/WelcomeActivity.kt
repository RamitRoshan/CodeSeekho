package com.example.codeseekho

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codeseekho.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var splashBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = splashBinding.root

        enableEdgeToEdge()
        setContentView(view)

        // Load custom fade-in animation
        val alphaAnimation = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in)

        splashBinding.textViewSplash.startAnimation(alphaAnimation)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                 val intent = Intent(this@WelcomeActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)



        // Apply animation to the main view
      //  splashBinding.main.startAnimation(alphaAnimation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}