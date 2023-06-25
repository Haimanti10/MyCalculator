package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNum: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNum = true
        lastDot = false
//Toast.makeText(this,"Button pressed",Toast.LENGTH_LONG).show()
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDec(view: View) {
        if (lastNum && !lastDot) {
            tvInput?.append(".")
            lastNum = false
            lastDot = true
        }
    }
    fun onOperator(view:View){
        tvInput?.text?.let{

            if(lastNum && !isOperatorAdded(it.toString())){//if the last char is a num and there is no operator added then execute
                tvInput?.append((view as Button).text)
                lastNum=false
                lastDot=false
            }
        }

    }
    fun onEqual(view: View){
        if(lastNum){
            var tvValue= tvInput?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]//splits 99-1 to 99 and 1
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    //var result =
                    tvInput?.text = removeZeoAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]//splits 99-1 to 99 and 1
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    //var result =
                    tvInput?.text = removeZeoAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]//splits 99-1 to 99 and 1
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    //var result =
                    tvInput?.text = removeZeoAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]//splits 99-1 to 99 and 1
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    //var result =
                    tvInput?.text = removeZeoAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }
            catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeoAfterDot(result:String): String{
        var value=result
        if(result.contains("0"))
            value=result.substring(0,result.length-2)
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }
}
