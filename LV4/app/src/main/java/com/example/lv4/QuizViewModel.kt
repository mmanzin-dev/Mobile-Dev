package com.example.lv4

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

private val questionList = listOf(
    Question(R.string.question_city, false),
    Question(R.string.question_city2, true),
    Question(R.string.question_city3,false),
    Question(R.string.question_city4,true),
    Question(R.string.question_city5,false))

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionList[currentIndex].answer

    val currentQuestionText: Int
        get() = questionList[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionList.size
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex - 1 < 0) {
            questionList.size - 1
        } else {
            currentIndex - 1
        }
    }
}