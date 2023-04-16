package com.example.fluently

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fluently.databinding.ActivityMainBinding
import com.example.fluently.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    private lateinit var bindingM : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingM=inflate(layoutInflater)
        setContentView(bindingM.root)
    }
    fun goToChooseLang(@Suppress("UNUSED_PARAMETER")view : View){
        val i= Intent(this, ChooseLangActivity::class.java)
        startActivity(i)
    }
    fun goToDescription(@Suppress("UNUSED_PARAMETER")view : View){
        val i= Intent(this, DescriptionActivity::class.java)
        startActivity(i)
    }
}