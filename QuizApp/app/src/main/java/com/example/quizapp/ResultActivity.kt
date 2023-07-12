package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvUser: TextView = findViewById(R.id.UI_TV_NAME)
        val tvScore: TextView = findViewById(R.id.UI_TV_SC)
        val btnFns: Button = findViewById(R.id.UI_BTN_FNS)

        tvUser.text = intent.getStringExtra(Constants.USER_NAME)
        tvScore.text = "Your Score was ${intent.getStringExtra(Constants.CORRECT_ANSWERS)}/${intent.getStringExtra(Constants.TOTAL_QUESTIONS)}!"

        btnFns.setOnClickListener{
            val myNewIntent = Intent(this, MainActivity::class.java)
            startActivity(myNewIntent)
            finish()

        }
    }
}