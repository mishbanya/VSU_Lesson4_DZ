package com.example.vsu_lesson4_dz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class AgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_age)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            finishAffinity()
        }
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            Toast.makeText(this, intent.getStringExtra("NAME") + " " + intent.getStringExtra("SURNAME") + " " +  findViewById<TextInputEditText>(R.id.ageInput).text, Toast.LENGTH_SHORT).show()
        }
    }
}