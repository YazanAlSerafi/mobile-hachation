package com.example.mobile_hachation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name.isNotEmpty()) {
                val intent = Intent(this, TaskActivity::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ادخل اسمك أولًا", Toast.LENGTH_SHORT).show()
            }


            }
        }
    }
