package com.example.lv3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtQuestion: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    private lateinit var btnPrev: Button

    private val questionList = listOf(
        Question(R.string.question_city, false),
        Question(R.string.question_city2, true),
        Question(R.string.question_city3,false),
        Question(R.string.question_city4,true),
        Question(R.string.question_city5,false))

    private var index = 0

    private val stanjeCiklusa = "KvizStanje"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d(stanjeCiklusa, "Aktivnost kreirana")

        txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        btnTrue = findViewById<Button>(R.id.btnTrue)
        btnFalse = findViewById<Button>(R.id.btnFalse)
        btnNext = findViewById<Button>(R.id.btnNext)
        btnPrev = findViewById<Button>(R.id.btnPrev)

        updateQuestion()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            index = (index + 1) % questionList.size
            updateQuestion()
        }

        btnPrev.setOnClickListener {
            index = if (index - 1 < 0) questionList.size - 1 else index - 1
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        val questionTextResId = questionList[index].textResId
        txtQuestion.setText(questionTextResId)
    }

    fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionList[index].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.toast_true
        } else {
            R.string.toast_false
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(stanjeCiklusa, "Aktivnost nastavljena")
    }

    override fun onPause() {
        super.onPause()
        Log.d(stanjeCiklusa, "Aktivnost pauzirana")
    }

    override fun onStop() {
        super.onStop()
        Log.d(stanjeCiklusa, "Aktivnost zaustavljena")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(stanjeCiklusa, "Aktivnost unistena")
    }
}