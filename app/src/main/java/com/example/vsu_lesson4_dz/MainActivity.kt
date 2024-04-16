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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val name : String = result.data?.getStringExtra("NAME").toString()
                val surname : String = result.data?.getStringExtra("SURNAME").toString()
                val age : String = result.data?.getStringExtra("AGE").toString()
                Toast.makeText(this, "Добро пожаловать, $name $surname, $age", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            val newIntent: Intent = Intent(this, NameActivity::class.java)
            startForResultLauncher.launch(newIntent)
        }
    }
}