package com.example.fluently

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseLangActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_lang)
    }

    fun backToMain(view: View){
        finish()
    }

    fun goToChooseDifficult(view : View){
        when (view.id) {
            R.id.eng_button -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "en")
                startActivity(i)
            }
            R.id.ger_button -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "ger")
                startActivity(i)
            }
            R.id.pol_button -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "pl")
                startActivity(i)
            }
        }
    }
}