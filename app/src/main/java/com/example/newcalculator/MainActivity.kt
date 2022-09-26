package com.example.newcalculator

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.newcalculator.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity(), Parcelable {

    private var isANumber = true
    private lateinit var binding: ActivityMainBinding
    private val displayTV = binding.displayTV

    constructor(parcel: Parcel) : this() {
        isANumber = parcel.readByte() != 0.toByte()
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.oneBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.oneBTN.text)
                isANumber = true
            }
        }
        binding.twoBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.twoBTN.text)
                isANumber = true
            }
        }
        binding.threeBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.threeBTN.text)
                isANumber = true
            }
        }
        binding.fourBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.fourBTN.text)
                isANumber = true
            }
        }
        binding.fiveBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.fiveBTN.text)
                isANumber = true
            }
        }
        binding.sixBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.sixBTN.text)
                isANumber = true
            }
        }
        binding.sevenBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.sevenBTN.text)
                isANumber = true
            }
        }
        binding.eightBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.eightBTN.text)
                isANumber = true
            }
        }
        binding.nineBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.nineBTN.text)
                isANumber = true
            }
        }
        binding.zeroBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.zeroBTN.text)
                isANumber = true
            }
        }
        binding.addBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.addBTN.text)
                isANumber = false
            }
        }
        binding.subtractBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.subtractBTN.text)
                isANumber = false
            }
        }
        binding.multiplyBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.multiplyBTN.text)
                isANumber = false
            }
        }
        binding.divideBTN.setOnClickListener{
            if (isANumber) {
                displayTV.append(binding.divideBTN.text)
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isANumber) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}