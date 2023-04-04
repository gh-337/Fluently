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
            R.id.engButton -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "eng")
                startActivity(i)
            }
            R.id.gerButton -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "deutsch")
                startActivity(i)
            }
            R.id.polButton -> {
                var i = Intent(this, ChooseDifficultActivity::class.java)
                i.putExtra(Const.LANGUAGE, "poland")
                startActivity(i)
            }
        }
    }
}