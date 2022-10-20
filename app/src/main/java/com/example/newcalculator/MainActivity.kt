package com.example.newcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newcalculator.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private var operatorPressed = false
    private var isANumber = false
    private lateinit var binding: ActivityMainBinding

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
        var currentNumber = ""
        var operator = ""
        var firstNumber: Int? = null
        var secondNumber: Int? = null

        oneBtn.setOnClickListener {
            workingTV.append("1")
            currentNumber += "1"
            isANumber = true
        }
        twoBtn.setOnClickListener {
            workingTV.append("2")
            currentNumber += "2"
            isANumber = true
        }
        threeBtn.setOnClickListener {
            workingTV.append("3")
            currentNumber += "3"
            isANumber = true
        }
        fourBtn.setOnClickListener {
            workingTV.append("4")
            currentNumber += "4"
            isANumber = true
        }
        fiveBtn.setOnClickListener {
            workingTV.append("5")
            currentNumber += "5"
            isANumber = true
        }
        sixBtn.setOnClickListener {
            workingTV.append("6")
            currentNumber += "6"
            isANumber = true
        }
        sevenBtn.setOnClickListener {
            workingTV.append("7")
            currentNumber += "7"
            isANumber = true
        }
        eightBtn.setOnClickListener {
            workingTV.append("8")
            currentNumber += "8"
            isANumber = true
        }
        nineBtn.setOnClickListener {
            workingTV.append("9")
            currentNumber += "9"
            isANumber = true
        }
        zeroBtn.setOnClickListener {
            workingTV.append("0")
            currentNumber += "0"
            isANumber = true
        }
        addBtn.setOnClickListener {
            if (!operatorPressed && isANumber) {
                workingTV.append("+")
                operator = "+"
                firstNumber = currentNumber.toInt()
                operatorPressed = true
            } else if (operatorPressed && isANumber) {
                secondNumber = currentNumber.toInt()
                displayTV.text =
                    calculateResult(firstNumber!!, operator, secondNumber!!).toString()
                workingTV.text = ""
                operator = ""
                operatorPressed = false
                firstNumber = null
                secondNumber = null
            }
            isANumber = false
            currentNumber = ""
        }
        subtractBtn.setOnClickListener {
            if (!operatorPressed && isANumber) {
                workingTV.append("-")
                operator = "-"
                firstNumber = currentNumber.toInt()
                operatorPressed = true
            } else if (operatorPressed && isANumber) {
                secondNumber = currentNumber.toInt()
                displayTV.text =
                    calculateResult(firstNumber!!, operator, secondNumber!!).toString()
                workingTV.text = ""
                operator = ""
                operatorPressed = false
                firstNumber = null
                secondNumber = null
            }
            isANumber = false
            currentNumber = ""
        }
        multiplyBtn.setOnClickListener {
            if (!operatorPressed && isANumber) {
                workingTV.append("*")
                operator = "*"
                firstNumber = currentNumber.toInt()
                operatorPressed = true
            } else if (operatorPressed && isANumber) {
                secondNumber = currentNumber.toInt()
                displayTV.text =
                    calculateResult(firstNumber!!, operator, secondNumber!!).toString()
                workingTV.text = ""
                operator = ""
                operatorPressed = false
                firstNumber = null
                secondNumber = null
            }
            isANumber = false
            currentNumber = ""
        }
        divideBtn.setOnClickListener {
            if (!operatorPressed && isANumber) {
                workingTV.append("/")
                operator = "/"
                firstNumber = currentNumber.toInt()
                operatorPressed = true
            } else if (operatorPressed && isANumber) {
                secondNumber = currentNumber.toInt()
                displayTV.text =
                    calculateResult(firstNumber!!, operator, secondNumber!!).toString()
                workingTV.text = ""
                operator = ""
                operatorPressed = false
                firstNumber = null
                secondNumber = null
            }
            isANumber = false
            currentNumber = ""
        }
        clearBtn.setOnClickListener {
            workingTV.text = ""
            isANumber = false
            operatorPressed = false
            currentNumber = ""
            firstNumber = null
            secondNumber = null

        }
        allClearBtn.setOnClickListener {
            workingTV.text = ""
            displayTV.text = ""
            isANumber = false
            operatorPressed = false
            currentNumber = ""
            firstNumber = null
            secondNumber = null
        }
        backspaceBtn.setOnClickListener {
            val length = workingTV.length()
            if (length > 0) {
                workingTV.text = workingTV.text.subSequence(0, length - 1)

                if (!operatorPressed && isANumber) {
                    if (currentNumber != "") {
                        firstNumber = currentNumber.toInt()
                        currentNumber =
                            currentNumber.subSequence(0, currentNumber.length - 1).toString()
                    } else {
                        firstNumber = null
                    }
                }

                if (operatorPressed && isANumber) {

                    if (currentNumber != "") {
                        secondNumber = currentNumber.toInt()
                        currentNumber =
                            currentNumber.subSequence(0, currentNumber.length - 1).toString()
                    } else {
                        secondNumber = null
                    }
                }

                if (operatorPressed && !isANumber) {
                    operator = ""
                    operatorPressed = false
                    isANumber = true
                }
            } else {
                firstNumber = null
                secondNumber = null
                operator = ""
                currentNumber = ""
                isANumber = false
                operatorPressed = false
            }
        }
        resultBtn.setOnClickListener {
            secondNumber = currentNumber.toInt()
            displayTV.text =
                calculateResult(firstNumber!!, operator, secondNumber!!).toString()
            firstNumber = null
            secondNumber = null
            operator = ""
            currentNumber = ""
            isANumber = false
            operatorPressed = false
        }
    }

    private fun calculateResult(x: Int, operator: String, y: Int): Int? {
        var result: Int? = null

        when (operator) {
            "+" -> {
                result = x + y
            }
            "-" -> {
                result = x - y
            }
            "*" -> {
                result = x * y
            }
            "/" -> {
                try {
                    result = x / y
                } catch (_: java.lang.ArithmeticException) {
                }
            }
        }
        return result
    }
}