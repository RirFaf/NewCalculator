package com.example.newcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newcalculator.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private var isANumber = false
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val oneBtn = binding.oneBtn
        val twoBtn = binding.twoBtn
        val threeBtn = binding.threeBtn
        val fourBtn = binding.fourBtn
        val fiveBtn = binding.fiveBtn
        val sixBtn = binding.sixBtn
        val sevenBtn = binding.sevenBtn
        val eightBtn = binding.eightBtn
        val nineBtn = binding.nineBtn
        val zeroBtn = binding.zeroBtn
        val addBtn = binding.addBtn
        val subtractBtn = binding.subtractBtn
        val multiplyBtn = binding.multiplyBtn
        val divideBtn = binding.divideBtn
        val resultBtn = binding.resultBtn
        val backspaceBtn = binding.backspaceBtn
        val allClearBtn = binding.allClearBtn
        val clearBtn = binding.clearBtn
        val displayTV = binding.displayTV
        val workingTV = binding.workingTV

        oneBtn.setOnClickListener {
            displayTV.append("1")
            isANumber = true
        }
        twoBtn.setOnClickListener {
            displayTV.append("2")
            isANumber = true
        }
        threeBtn.setOnClickListener {
            displayTV.append("3")
            isANumber = true
        }
        fourBtn.setOnClickListener {
            displayTV.append("4")
            isANumber = true
        }
        fiveBtn.setOnClickListener {
            displayTV.append("5")
            isANumber = true
        }
        sixBtn.setOnClickListener {
            displayTV.append("6")
            isANumber = true
        }
        sevenBtn.setOnClickListener {
            displayTV.append("7")
            isANumber = true
        }
        eightBtn.setOnClickListener {
            displayTV.append("8")
            isANumber = true
        }
        nineBtn.setOnClickListener {
            displayTV.append("9")
            isANumber = true
        }
        zeroBtn.setOnClickListener {
            displayTV.append("0")
            isANumber = true
        }
        addBtn.setOnClickListener {
            displayTV.append("+")
            isANumber = false
        }
        subtractBtn.setOnClickListener {
            displayTV.append("-")
            isANumber = false
        }
        multiplyBtn.setOnClickListener {
            displayTV.append("*")
            isANumber = false
        }
        divideBtn.setOnClickListener {
            displayTV.append("/")
            isANumber = false
        }
        clearBtn.setOnClickListener {
            workingTV.text = ""
        }
        allClearBtn.setOnClickListener {
            displayTV.text = ""
            workingTV.text = ""
        }
        backspaceBtn.setOnClickListener {
            val length = workingTV.length()
            if (length > 0) {
                workingTV.text = workingTV.text.subSequence(0, length - 1)
            }
        }
        resultBtn.setOnClickListener {
        }
    }
}

//    private fun calculateResult(): String {
//        val stml = stringToMutableList()
//        if (stml.isEmpty()) {
//            return ""
//        }
//
//        val timesDivision = calculatePrior(stml)
//        if (timesDivision.isEmpty()) {
//            return ""
//        }
//
//        val result = addSubtractCalculate(timesDivision)
//        return result.toString()
//    }
//
//    private fun addSubtractCalculate(passedList: MutableList<Any>): Int {
//        var result = passedList[0] as Int
//
//        for (i in passedList.indices) {
//            if (passedList[i] is Char && i != passedList.lastIndex) {
//                val operator = passedList[i]
//                val nextDigit = passedList[i + 1] as Int
//                if (operator == "+") {
//                    result += nextDigit
//                }
//                if (operator == "-") {
//                    result -= nextDigit
//                }
//            }
//        }
//        return result
//    }
//
//    private fun calculateMultDiv(passedList: MutableList<Any>): MutableList<Any> {
//
//        val newList = mutableListOf<Any>()
//        var restartIndex = passedList.size
//
//        for (i in passedList.indices) {
//            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
//
//                val operator = passedList[i]
//                val previousDigit = passedList[i - 1] as Int
//                val nextDigit = passedList[i + 1] as Int
//
//                when (operator) {
//                    "*" -> {
//                        newList.add(previousDigit * nextDigit)
//                        restartIndex = i + 1
//                    }
//                    "/" -> {
//                        newList.add(previousDigit / nextDigit)
//                        restartIndex = i + 1
//                    }
//                    else -> {
//                        newList.add(previousDigit)
//                        newList.add(operator)
//                    }
//                }
//            }
//
//            if (i > restartIndex) {
//                newList.add(passedList[i])
//            }
//        }
//        return newList
//    }
//
//    private fun calculatePrior(passedList: MutableList<Any>): MutableList<Any> {
//        var list = passedList
//        while (list.contains("*") || list.contains("/")) {
//            list = calculateMultDiv(list)
//        }
//        return list
//    }
//
//    private fun stringToMutableList(): MutableList<Any> {
//
//        val list = mutableListOf<Any>()
//        var currentDigit = ""
//
//        for (character in binding.displayTV.text) {
//            if (character.isDigit()) {
//                currentDigit += character
//            } else {
//                list.add(currentDigit.toInt())
//                currentDigit = ""
//                list.add(character)
//            }
//
//            if (currentDigit != "") {
//                list.add(currentDigit.toInt())
//            }
//        }
//        return list
//    }
