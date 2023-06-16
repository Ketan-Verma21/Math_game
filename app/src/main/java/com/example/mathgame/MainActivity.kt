package com.example.mathgame

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var addition:Button
    lateinit var subtraction:Button
    lateinit var multiplication:Button
    private var backpressedtime=0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addition=findViewById(R.id.buttonadd)
        subtraction=findViewById(R.id.buttonsub)
        multiplication=findViewById(R.id.buttonmulti)


        addition.setOnClickListener {
            val intent= Intent(this@MainActivity,Gameactivity::class.java)
            intent.putExtra("operator","+")
            startActivity(intent)
        }
        subtraction.setOnClickListener {
            val intent= Intent(this@MainActivity,Gameactivity::class.java)
            intent.putExtra("operator","-")

            startActivity(intent)
        }
        multiplication.setOnClickListener {
            val intent= Intent(this@MainActivity,Gameactivity::class.java)
            intent.putExtra("operator","*")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if(backpressedtime+2000>System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(applicationContext,"Press back again to exit",Toast.LENGTH_SHORT).show()
        }
        backpressedtime=System.currentTimeMillis()
    }


}