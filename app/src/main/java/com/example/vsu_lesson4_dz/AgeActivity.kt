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
        var name : String? = null
        var surname : String? = null
        intent.extras?.let {screenData ->
            name = screenData.getString("NAME").toString()
            surname = screenData.getString("SURNAME").toString()
        }
        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            finishAffinity()
        }
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            val ageInput = findViewById<TextInputEditText>(R.id.ageInput).text.toString()

            if (ageInput.isNotBlank()) {
                val resultIntent = Intent().apply {
                    putExtra("NAME", name)
                    putExtra("SURNAME", surname)
                    putExtra("AGE", ageInput)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Введите возраст", Toast.LENGTH_SHORT).show()
            }
        }
    }
}