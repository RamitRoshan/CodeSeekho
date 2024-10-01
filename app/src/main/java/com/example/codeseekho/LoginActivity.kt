package com.example.codeseekho
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codeseekho.databinding.ActivityLoginBinding
import com.example.codeseekho.view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    val auth  = FirebaseAuth.getInstance()

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        enableEdgeToEdge()

        setContentView(view)

        val textOfGoogleButton = loginBinding.buttonGoogleSignin.getChildAt(0) as TextView
        textOfGoogleButton.text = "Continue with Google"
        textOfGoogleButton.setTextColor(Color.BLACK)
        textOfGoogleButton.textSize = 18F

        loginBinding.buttonSignin.setOnClickListener {

            val userEmail = loginBinding.editTextLoginEmail.text.toString()
            val userPassword = loginBinding.editTextLoginPassword.text.toString()

            signInUser(userEmail, userPassword)

        }


        loginBinding.buttonGoogleSignin.setOnClickListener {

           // signInGoogle()
        }


        loginBinding.textViewSignup.setOnClickListener {

            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        loginBinding.textViewForgotPassword.setOnClickListener {

            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun signInUser(userEmail : String, userPassword : String){
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful){

                Toast.makeText(applicationContext,"Welcome to Code Seekho",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(applicationContext,task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()

            }
        }

    }
    //onstart fn checks user is logged in or not
    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if(user != null){
            Toast.makeText(applicationContext,"Welcome to Code Seekho",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

//    private fun signInGoogle(){
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken()
//    }
}