package com.example.vsu_lesson4_dz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
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
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            val newIntent : Intent = Intent(this, AgeActivity::class.java)
            newIntent.putExtra("NAME", name)
            newIntent.putExtra("SURNAME", findViewById<TextInputEditText>(R.id.surnameInput).text.toString())
            startActivity(newIntent)
        }

    }
}