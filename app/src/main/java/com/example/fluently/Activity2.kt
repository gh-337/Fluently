package com.example.fluently

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.fluently.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private lateinit var bindingS: Activity2Binding
    private var room = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        bindingS = Activity2Binding.inflate(layoutInflater)
        setContentView(bindingS.root)

        bindingS.SearchEdit.setOnKeyListener { _, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                if (bindingS.SearchEdit.text.toString() != "") {
                    finish()
                } else
                    Toast.makeText(this, "Введіть номер приміщення", Toast.LENGTH_SHORT).show()

                return@setOnKeyListener true
            } else {

                false
            }
        }
    }
}