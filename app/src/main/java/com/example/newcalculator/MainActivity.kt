package com.example.newcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {

    private var isANumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun numberAction(view: View) {
        if (view is Button) {
            displayTV.append(view.text)
            isANumber = true
        }
    }

    fun operatorAction(view: View) {
        if (view is Button && isANumber) {
            displayTV.append(view.text)
            isANumber = false
        }
    }

    fun clearAction(view: View) {
        displayTV.text = ""
    }

    fun backspaceAction(view: View) {
        val length = displayTV.length()
        if (length > 0) {
            displayTV.text = displayTV.text.subSequence(0, length - 1)
        }
    }

    fun resultAction(view: View) {
        displayTV.text = calculateResult()
    }

    private fun calculateResult(): String {
        val stml = stringToMutableList()
        if (stml.isEmpty()) {
            return ""
        }

        val timesDivision = calculatePrior(stml)
        if (timesDivision.isEmpty()) {
            return ""
        }

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Int {
        var result = passedList[0] as Int

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Int
                if (operator == "+") {
                    result += nextDigit
                }
                if (operator == "-") {
                    result -= nextDigit
                }
            }
        }
        return result
    }

    private fun calculateMultDiv(passedList: MutableList<Any>): MutableList<Any> {

        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {

                val operator = passedList[i]
                val previousDigit = passedList[i - 1] as Int
                val nextDigit = passedList[i + 1] as Int

                when (operator) {
                    "*" -> {
                        newList.add(previousDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    "/" -> {
                        newList.add(previousDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(previousDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex) {
                newList.add(passedList[i])
            }
        }
        return newList
    }

    private fun calculatePrior(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains("*") || list.contains("/")) {
            list = calculateMultDiv(list)
        }
        return list
    }

    private fun stringToMutableList(): MutableList<Any> {

        val list = mutableListOf<Any>()
        var currentDigit = ""

        for (character in displayTV.text) {
            if (character.isDigit()) {
                currentDigit += character
            } else {
                list.add(currentDigit.toInt())
                currentDigit = ""
                list.add(character)
            }

            if (currentDigit != "") {
                list.add(currentDigit.toInt())
            }
        }
        return list
    }
}