package com.example.mathgame

import android.content.Intent
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.abs
import java.util.*
import kotlin.random.Random

class Gameactivity : AppCompatActivity() {
    lateinit var textScore:TextView
    lateinit var textLife:TextView
    lateinit var textTime:TextView
    lateinit var textQuestion:TextView
    lateinit var edittextanswer:EditText
    lateinit var buttonok:Button
    lateinit var buttonnext:Button
    lateinit var timer:CountDownTimer

    private val startTimerinmillis:Long=10000
    var timeleftinmillis:Long=startTimerinmillis
    var correctanswer=0
    var userscore=0
    var userlife=3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameactivity)
        supportActionBar!!.title="Addition"
        textScore=findViewById(R.id.textViewScore)
        textLife=findViewById(R.id.textViewlife)
        textTime=findViewById(R.id.textViewtime)
        textQuestion=findViewById(R.id.textViewQuestion)
        edittextanswer=findViewById(R.id.editTextAnswer)
        buttonok=findViewById(R.id.buttonOk)
        buttonnext=findViewById(R.id.buttonNext)
        gamecontinue()
        buttonok.setOnClickListener {
            val input=edittextanswer.text.toString()
            if(input==""){
                Toast.makeText(applicationContext,"Please enter the answer or click the next button",Toast.LENGTH_SHORT).show()

            }
            else{
                pauseTimer()
                val userAnswer=input.toInt()
                if(userAnswer==correctanswer){
                    userscore=userscore+10
                    textQuestion.text="Congratulations, your answer is correct . Please press NEXT button"
                    textScore.text=userscore.toString()
                }
                else{
                    userlife=userlife-1
                    textQuestion.text="Sorry, your answer is wrong"
                    edittextanswer.visibility=View.INVISIBLE
                    textLife.text=userlife.toString()
                }
            }
        }
        buttonnext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gamecontinue()
            edittextanswer.setText("")
            if(userlife<=0){
                Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@Gameactivity,finalactivity::class.java)
                intent.putExtra("score",userscore)
                startActivity(intent)
                finish()
            }
            else{
                gamecontinue()
            }
        }
    }
    fun gamecontinue(){
        val number1=Random.nextInt(0,100)
        val number2=Random.nextInt(0,100)
        val opr=intent.getStringExtra("operator")
        if(opr=="+"){
            textQuestion.text="$number1 + $number2"
            correctanswer=number1+number2
        }
        else if(opr=="-"){
            textQuestion.text="$number1 - $number2"
            correctanswer=abs(number1-number2)
        }
        else{
            textQuestion.text="$number1 X $number2"
            correctanswer=number1*number2
        }

        edittextanswer.visibility=View.VISIBLE
        starttimer()


    }
    fun starttimer(){
        timer=object:CountDownTimer(timeleftinmillis,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeleftinmillis=millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userlife--
                textLife.text=userlife.toString()
                textQuestion.text="Sorry, your time is up!!"
                edittextanswer.visibility=View.INVISIBLE
            }

        }.start()

    }
    fun updateText(){
        val remainingtime:Int=(timeleftinmillis/1000).toInt()
        textTime.text= String.format(Locale.getDefault(),"%02d",remainingtime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeleftinmillis=startTimerinmillis
        updateText()
    }
}