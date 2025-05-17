package com.example.mobile_hachation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            val Uname = nameEditText.text.toString()
            if (Uname.isNotEmpty()) {
                val intent = Intent(this, TaskActivity::class.java)
                intent.putExtra("USER_NAME", Uname)

                startActivity(intent)
            } else {
                Toast.makeText(this, "ادخل اسمك أولًا", Toast.LENGTH_SHORT).show()
            }


            }
        }
    }
