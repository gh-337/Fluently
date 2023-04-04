package com.example.fluently

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ChooseDifficultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_difficult)
    }

    fun backToChooseLang(view: View){
        finish()
    }

    fun goToEasyLevel(view : View){
        var i = Intent(this, EasyLevelActivity::class.java)
        i.putExtra(Const.LANGUAGE, intent.extras?.getString(Const.LANGUAGE))
        when (view.id) {
            R.id.imageButton4 -> {
                i.putExtra(Const.DIFFICULT, "easy")
            }
            R.id.imageButton5 -> {
                i.putExtra(Const.DIFFICULT, "middle")
            }
            R.id.imageButton6 -> {
                i.putExtra(Const.DIFFICULT, "hard")
            }
        }
        startActivity(i)
    }
}