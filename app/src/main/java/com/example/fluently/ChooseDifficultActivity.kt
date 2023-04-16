package com.example.fluently

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseDifficultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_difficult)
    }
    fun backToChooseLang(view: View){
        finish()
    }
    fun goToChooseLevel(view : View){
        var i = Intent(this, ChooseLevelActivity::class.java)
        i.putExtra(Const.LANGUAGE, intent.extras?.getString(Const.LANGUAGE))
        when (view.id) {
            R.id.easy_btn -> {
                i.putExtra(Const.DIFFICULT, "easy")
            }
            R.id.middle_btn -> {
                i.putExtra(Const.DIFFICULT, "middle")
            }
            R.id.hard_btn -> {
                i.putExtra(Const.DIFFICULT, "hard")
            }
        }
        startActivity(i)
    }
}