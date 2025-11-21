package com.example.myapplication


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val resultado = findViewById<EditText>(R.id.resultado)
        resultado.setText("")
        resultado.isFocusable = false
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val b3 = findViewById<Button>(R.id.button3)
        val b4 = findViewById<Button>(R.id.button4)
        val b5 = findViewById<Button>(R.id.button5)
        val b6 = findViewById<Button>(R.id.button6)
        val b7 = findViewById<Button>(R.id.button7)
        val b8 = findViewById<Button>(R.id.button8)
        val b9 = findViewById<Button>(R.id.button9)
        val b0 = findViewById<Button>(R.id.button0)
        val bPlus = findViewById<Button>(R.id.buttonPlus)
        val bRest = findViewById<Button>(R.id.buttonRest)
        val bMult = findViewById<Button>(R.id.buttonMul)
        val bDivi = findViewById<Button>(R.id.buttonDiv)
        val bC = findViewById<Button>(R.id.buttonC)
        val vEqual = findViewById<Button>(R.id.buttonEqual)
        vEqual.setOnClickListener { calculate() }
        bC.setOnClickListener { removeCharacter() }
        val buttons = listOf<Button>(b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bPlus,bRest,bMult,bDivi)
        buttons.forEach { button -> button.setOnClickListener{addCharToEditText(button.text.toString())} }
    }
    fun addCharToEditText(charac: String){
        val resultado = findViewById<EditText>(R.id.resultado)
        if (((charac!="0" && charac !in "*+-/")||resultado.text.toString()!="") && (charac !in "+-*/" || resultado.text[resultado.text.length-1] !in "+-*/")){
            resultado.setText(resultado.text.toString() + charac)
        }
    }
    fun removeCharacter(){
        val resultado = findViewById<EditText>(R.id.resultado)
        if(resultado.length()>0)
            resultado.setText(resultado.text.substring(0 until resultado.text.length-1))
    }

    fun calculate(){
        val resultado = findViewById<EditText>(R.id.resultado)
        if(resultado.text.isNotEmpty() && (resultado.text[resultado.text.length-1] !in "+-*/")) {
            var nuevoRes =
                ExpressionBuilder(resultado.text.toString()).build().evaluate().toString()
            if(nuevoRes.substring(nuevoRes.length-2..<nuevoRes.length)==".0") nuevoRes = nuevoRes.substring(0..nuevoRes.length-3)
            resultado.setText(nuevoRes)
        }
    }


}