package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast

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
        val AllNumbersTest: Button = findViewById<Button>(R.id.AllNumbersTest)
        val inputNumberEditText: EditText = findViewById(R.id.inputNumber)
        val selectiveExerciseButton: Button = findViewById(R.id.selectiveExercise)

        AllNumbersTest.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

        selectiveExerciseButton.setOnClickListener {
            val inputText = inputNumberEditText.text.toString()
            val number = inputText.toIntOrNull()

            if (number != null && number in 2..9) {
                // Передаем введенное пользователем число в SelectiveTestActivity
                val intent = Intent(this, SelectiveTestActivity::class.java)
                intent.putExtra("SELECTED_NUMBER", number)
                startActivity(intent)
            } else {
                // Показываем сообщение об ошибке, если введено не число от 2 до 9
                Toast.makeText(this, "Пожалуйста, введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            }
        }

    }
}