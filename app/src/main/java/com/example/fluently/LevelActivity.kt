package com.example.fluently

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fluently.databinding.ActivityEasyLevelBinding
import com.example.fluently.databinding.ActivityLevelBinding

class LevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Toast.makeText(this, intent.extras?.getString("Lang").toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getString(Const.LANGUAGE), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getString(Const.DIFFICULT), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getInt(Const.LEVEL).toString(), Toast.LENGTH_SHORT).show()
    }

    /*     має бути перевірка...

            when (intent.extras?.getString(Const.LANGUAGE)) {
            "eng" -> {
                Toast.makeText(this, "eng", Toast.LENGTH_SHORT).show()
            }
            "deutsch" -> {
                Toast.makeText(this, "deutsch", Toast.LENGTH_SHORT).show()
            }
            "poland" -> {
                Toast.makeText(this, "poland", Toast.LENGTH_SHORT).show()
            }
        }*/

    fun backToLevels(view: View){
        finish()
    }
}