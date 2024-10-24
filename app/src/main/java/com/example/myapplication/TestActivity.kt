package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class TestActivity : AppCompatActivity() {
    private var correctAnswers = 0
    private var questionCount = 0
    private val totalQuestions = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val questionTextView: TextView = findViewById(R.id.questionTextView)
        val answerEditText: EditText = findViewById(R.id.answerEditText)
        val checkButton: Button = findViewById(R.id.checkButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val remainingQuestionsTextView: TextView = findViewById(R.id.remainingQuestionsTextView)
        val backButton: Button = findViewById(R.id.backButton)

        var num1 = 0
        var num2 = 0

        // Функция для генерации нового вопроса
        fun generateNewQuestion() {
            num1 = Random.nextInt(1, 10)
            num2 = Random.nextInt(1, 10)
            questionTextView.text = "Сколько будет $num1 * $num2?"
            answerEditText.text.clear()
            resultTextView.text = ""

            // Обновляем счетчик оставшихся вопросов
            remainingQuestionsTextView.text = "Осталось вопросов: ${totalQuestions - questionCount}"
        }

        // Генерация первого вопроса
        generateNewQuestion()

        checkButton.setOnClickListener {
            val userAnswer = answerEditText.text.toString().toIntOrNull()
            if (userAnswer != null) {
                questionCount++

                if (userAnswer == num1 * num2) {
                    correctAnswers++
                    resultTextView.text = "Правильный ответ"
                } else {
                    resultTextView.text = "Неверный ответ"
                }

                // Проверяем, завершился ли тест
                if (questionCount < totalQuestions) {
                    generateNewQuestion()
                } else {
                    // Когда все вопросы заданы, тест завершен
                    val percentage = (correctAnswers * 100) / totalQuestions
                    resultTextView.text = "Тест завершен. Правильных ответов: $percentage%"
                    remainingQuestionsTextView.text = "Осталось вопросов: 0"
                    checkButton.isEnabled = false
                }
            } else {
                resultTextView.text = "Пожалуйста, введите число"
            }
        }

        // Кнопка для возвращения на главную активность
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрыть текущую активность, чтобы предотвратить возвращение назад
        }
    }
}


