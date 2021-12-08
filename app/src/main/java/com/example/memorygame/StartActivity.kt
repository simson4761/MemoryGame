package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val name : EditText = findViewById(R.id.name)
        val start : ImageView = findViewById(R.id.startGame)
        val mode : Switch = findViewById(R.id.switch2)
        val babyMessage = arrayListOf("wanna change your diaper?"," ENNA THALA avalodhana???","you wanna goto mommy?","B for baby")
        val hardMessage = arrayListOf("YEAH BOY!!!","LESS GOOOO","VERA MARI....VERA MARI")
        var gameMode = "easy"
        mode.setOnClickListener {
            if (mode.isChecked){
                gameMode = "hard"
                hardMessage.shuffle()
                Toast.makeText(this@StartActivity,hardMessage[0],Toast.LENGTH_SHORT).show()
            }
            else {
                babyMessage.shuffle()
                gameMode = "easy"
                Toast.makeText(this@StartActivity, babyMessage[0], Toast.LENGTH_SHORT).show()
            }
        }

        start.isClickable
        start.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("Data",name.text.toString())
                putExtra("mode",gameMode)
            }
            val username = name.text.toString().trim()
            if(username.isEmpty()) {
                Toast.makeText(this@StartActivity,"please enter name",Toast.LENGTH_SHORT).show()
            }
            else
            startActivity(switchActivityIntent)

        }

    }
}