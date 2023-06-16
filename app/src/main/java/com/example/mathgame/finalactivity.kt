package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class finalactivity : AppCompatActivity() {
    lateinit var result:TextView
    lateinit var playagain:Button
    lateinit var exit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalactivity)
        result=findViewById(R.id.textViewfinalscore)
        playagain=findViewById(R.id.buttonagain)
        exit=findViewById(R.id.buttonexit)
        val score=intent.getIntExtra("score",0)
        result.text="Your score : "+score
        playagain.setOnClickListener {
            val intent=Intent(this@finalactivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}