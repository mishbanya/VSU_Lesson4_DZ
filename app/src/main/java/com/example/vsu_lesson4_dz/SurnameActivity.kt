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

class SurnameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_surname)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var name : String? = null
        intent.extras?.let {screenData ->
            name = screenData.getString("NAME").toString()
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
            val surnameInput = findViewById<TextInputEditText>(R.id.surnameInput).text.toString()
            if (surnameInput.isNotBlank()) {
                val newIntent = Intent(this, AgeActivity::class.java)
                newIntent.putExtra("SURNAME", surnameInput)
                startForResultLauncher.launch(newIntent)
            } else {
                Toast.makeText(this, "Введите фамилию", Toast.LENGTH_SHORT).show()
            }
        }

    }
}