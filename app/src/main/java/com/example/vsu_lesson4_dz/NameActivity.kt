package com.example.vsu_lesson4_dz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_name)
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
        val startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val name : String = result.data?.getStringExtra("NAME").toString()
                val surname : String = result.data?.getStringExtra("SURNAME").toString()
                val age : String = result.data?.getStringExtra("AGE").toString()
                val resultIntent = Intent().apply {
                    putExtra("NAME", name)
                    putExtra("SURNAME", surname)
                    putExtra("AGE", age)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.nameInput).text.toString()
            if (nameInput.isNotBlank()) {
                val newIntent = Intent(this, SurnameActivity::class.java)
                newIntent.putExtra("NAME", nameInput)
                startForResultLauncher.launch(newIntent)
            } else {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
            }
        }
    }
}