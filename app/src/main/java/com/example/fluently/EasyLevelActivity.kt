package com.example.fluently

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fluently.databinding.ActivityEasyLevelBinding


class EasyLevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEasyLevelBinding

    private val btnTextList = listOf("1","2","3","4","5","6","7","8","9","10","11",
        "12","13","14","15","16","17","18","19","20")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEasyLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){

        val search = ArrayList<ButtonLevel>()
        for (index in 0 until (btnTextList.size)) {
            val txt1 = btnTextList[index]
            search.add(index, ButtonLevel(txt1))
        }
        binding.apply {
            btnRecViev.layoutManager = GridLayoutManager(this@EasyLevelActivity, 5)
            btnRecViev.adapter = ButtonLevelAdapter(search)
        }
    }

    fun backToChooseDifficult(@Suppress("UNUSED_PARAMETER")view: View) {
        finish()
    }

    fun difcolor(): String{
        return when (intent.extras?.getString(Const.DIFFICULT)) {
            "easy" -> "greenBtnColor"
            "middle" -> "yellowBtnColor"
            "hard" -> "redBtnColor"
            else -> {"greenBtnColor"}
        }
    }

    fun goToLevel(Level : Int){
        val i = Intent(this, LevelActivity::class.java)
        i.putExtra(Const.LANGUAGE, intent.extras?.getString(Const.LANGUAGE))
        i.putExtra(Const.DIFFICULT, intent.extras?.getString(Const.DIFFICULT))
        i.putExtra(Const.LEVEL, Level)
        setResult(RESULT_OK, i)
        startActivity(i)
    }
}