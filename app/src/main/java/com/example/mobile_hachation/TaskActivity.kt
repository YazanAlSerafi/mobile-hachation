package com.example.mobile_hachation

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task)
        val SummaryFragment=SummaryFragment()
        val TODOFragment=TODOFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, TODOFragment())
            .commit()
        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.summary -> SummaryFragment()
                R.id.todo ->TODOFragment()
                else -> null
            }
            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, it)
                    .commit()}


            true }


    }
}