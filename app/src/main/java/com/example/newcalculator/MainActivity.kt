package com.example.newcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.newcalculator.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isANumber = false
    private lateinit var binding: ActivityMainBinding
    private val displayTV = binding.displayTV

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.oneBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.twoBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.threeBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.fourBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.fiveBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.sixBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.sevenBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.eightBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.nineBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.zeroBTN.setOnClickListener{
            if (view is Button && isANumber) {
                displayTV.append(view.text)
                isANumber = true
            }
        }
        binding.addBTN.setOnClickListener{
            if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
                displayTV.append(view.text)
                isANumber = false
            }
        }
        binding.subtractBTN.setOnClickListener{
            if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
                displayTV.append(view.text)
                isANumber = false
            }
        }
        binding.multiplyBTN.setOnClickListener{
            if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
                displayTV.append(view.text)
                isANumber = false
            }
        }
        binding.divideBTN.setOnClickListener{
            if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
                displayTV.append(view.text)
                isANumber = false
            }
        }
        binding.clearBTN.setOnClickListener{
            displayTV.text = ""
        }
        binding.backspaceBTN.setOnClickListener{
            if (binding.displayTV.length()>0){
                displayTV.text = displayTV.text.subSequence(0, displayTV.length()-1)

            }
        }
        binding.resultBTN.setOnClickListener{
            calculateResult()
        }
    }

//    fun numberBTNAction(view: View) {
//        if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
//            displayTV.append(view.text)
//            isANumber = true
//        }
//    }

//    fun operatorBTNAction(view: View) {
//        if (view is androidx.appcompat.widget.AppCompatButton && isANumber) {
//            displayTV.append(view.text)
//            isANumber = false
//        }
//    }

//    fun clearBTNAction(view: View) {
//        displayTV.text = "";
//    }

//    fun backspaceBTNAction(view: View) {
//        if (displayTV.length() > 0) {
//            displayTV.text = displayTV.text.subSequence(0, displayTV.length() - 1)
//        }
//    }

//    fun resultBTNAction(view: View) {
//        calculateResult()
//    }

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

        var list = mutableListOf<Any>()
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