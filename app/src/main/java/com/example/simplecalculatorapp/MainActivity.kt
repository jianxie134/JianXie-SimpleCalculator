package com.example.simplecalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val operandOne = findViewById<EditText>(R.id.operand_one)
        val operandTwo = findViewById<EditText>(R.id.operand_two)
        val operationSpinner = findViewById<Spinner>(R.id.operation_spinner)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val resultText = findViewById<TextView>(R.id.result_text)

        // Spinner Setup
        ArrayAdapter.createFromResource(
            this,
            R.array.arithmetic_operations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            operationSpinner.adapter = adapter
        }

        // Calculate Button
        calculateButton.setOnClickListener {
            val num1 = operandOne.text.toString().toDoubleOrNull()
            val num2 = operandTwo.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                resultText.text = "Please enter valid numbers."
                return@setOnClickListener
            }

            val result = when (operationSpinner.selectedItem.toString()) {
                "Addition" -> num1 + num2
                "Subtraction" -> num1 - num2
                "Multiplication" -> num1 * num2
                "Division" -> if (num2 != 0.0) num1 / num2 else "Cannot divide by zero"
                "Modulus" -> num1 % num2
                else -> ""
            }

            resultText.text = "Result: $result"
        }
    }
}