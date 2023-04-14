package com.example.fluently

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fluently.databinding.ActivityLevelBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.checkerframework.checker.units.qual.s
import java.io.File

//11111
var result=0

class LevelActivity : AppCompatActivity() {
    lateinit var binding: ActivityLevelBinding
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val language = intent.extras?.getString(Const.LANGUAGE).toString() //ger pl en
        val difficult = intent.extras?.getString(Const.DIFFICULT).toString() //easy middle hard
        val level = intent.extras?.getInt(Const.LEVEL).toString() //1 2 3
        val npp=1
        var id = ""
        result=0

        getDataAct(language, difficult, level, npp)

        }


    fun getDataAct(language:String, difficult:String, level:String, npp:Int ) {
        var txt="11"
        var en = ""
        var id = ""
        var ger = ""
        var pl = ""

        db.collection(difficult).document("level").collection(level)
            .get()
            .addOnSuccessListener {
                for (documentSnapshot: DocumentSnapshot in it.documents) {

                    if (documentSnapshot.id == npp.toString()) {
                        en = documentSnapshot.getString("en").toString()
                        id = documentSnapshot.getString("id").toString()
                        ger = documentSnapshot.getString("ger").toString()
                        pl = documentSnapshot.getString("pl").toString()
                        txt = "$id $en $ger $pl "
                        openImage(id, difficult, level)
                        val yourView = binding.WomanLevel

                        yourView.setOnTouchListener(object : View.OnTouchListener {
                            public val MAX_CLICK_DURATION = 200
                            public var startClickTime: Long = 0

                            override fun onTouch(v: View, event: MotionEvent): Boolean {
                                when (event.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        startClickTime = System.currentTimeMillis()
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        val clickDuration = System.currentTimeMillis() - startClickTime
                                        if (clickDuration < MAX_CLICK_DURATION) {
                                            // Виконуємо вашу функцію при подвійному кліку
                                            if (event.actionMasked == MotionEvent.ACTION_UP && event.getPointerCount() == 1) {
                                                val time = SystemClock.uptimeMillis().toInt()
                                                if (time - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                                                    when (language) {
                                                        "en" -> {hint(en)}
                                                        "pl" -> {hint(pl)}
                                                        "ger" -> {hint(ger)}
                                                    }
                                                }
                                                lastClickTime = time
                                            }
                                        }
                                    }
                                }
                                return true
                            }
                        })

                        /*----*/
                        binding.etImageId.setOnKeyListener { _, i, keyEvent ->
                            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                                if (binding.etImageId.text.toString() != "") {
                                    //finish()
                                    checkLang(language, difficult, level, npp, en, ger, pl)
                                } else
                                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

                                return@setOnKeyListener true
                            } else {

                                false
                            }
                        }
                        /*----*/



                        binding.getImage.setOnClickListener {
                            checkLang(language, difficult, level, npp, en, ger, pl)

                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.w("TAG", "Error getting documents: ")
            }

    }
    fun checkLang(language:String, difficult:String, level:String, npp:Int, en:String, ger:String, pl:String){
        var word=binding.etImageId.text.toString().toLowerCase()
    when (language) {
        "en" -> {
            var npp = npp + 1
            if(word == en || word == en + " ")
                result=result+10
                if (npp == 3) {
                    modalWindow()
                }
            getDataAct(language, difficult, level, npp)
        }
        "ger" -> {
            var npp = npp + 1
            if(word == ger || word == ger + " ")
                result=result+10
                if (npp == 11) {
                    modalWindow()
                }

            getDataAct(language, difficult, level, npp)
        }
        "pl" -> {
            var npp = npp + 1
            if(word == pl || word == pl + " ")
                result=result+10
                if (npp == 11) {
                    modalWindow()
                }

            getDataAct(language, difficult, level, npp)
        }
        else -> Toast.makeText(this, "Wrong language", Toast.LENGTH_SHORT).show()
    }}

    fun openImage(id : String, difficult: String, level: String)
    {
        binding.etImageId.setText("")
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$difficult/$level/$id.jpg")
        val localfile = File.createTempFile("tempImage", "jpg")//створюєм тимчасовий файл

        val progressDialog = ProgressDialog(this)//вивід текту поки чекаємо картинку
        progressDialog.setMessage("Fetching image....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        storageRef.getFile(localfile).addOnSuccessListener {
            if (progressDialog.isShowing)//якщо вікно є то воно закривається
                progressDialog.dismiss()


            val bitmap =
                BitmapFactory.decodeFile(localfile.absolutePath)//перетворює файл у зображення
            binding.imageView.setImageBitmap(bitmap)//отриманий об'єкт Bitmap встановлюється в якості зображення в ImageView

        }.addOnFailureListener {//якщо ні то

            if (progressDialog.isShowing)//якщо вікно є то воно закривається
                progressDialog.dismiss()

            Toast.makeText(this, "Failed to retrieve thr image", Toast.LENGTH_SHORT).show()
            //вивід на екран повідомлення що в нас помилка
        }

    }
    fun backToLevels(view: View){
        finish()
    }
    fun youPressButton(@Suppress("UNUSED_PARAMETER")view : View){
        Toast.makeText(this, "you pressed button OK", Toast.LENGTH_SHORT).show()
    }
    fun hint(lang : String){
        Toast.makeText(this, lang, Toast.LENGTH_SHORT).show()
    }
    /*new   -------*/
    fun hintOnClick(lang : String) {
        Toast.makeText(this, lang, Toast.LENGTH_SHORT).show()
    }
    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA = 300 // мінімальний інтервал часу між кліками
        private var lastClickTime: Int = 0
    }




    fun modalWindow() {
        val dialogBinding = layoutInflater.inflate(R.layout.my_custom_dialog,null)
        val resultLevel = dialogBinding.findViewById<TextView>(R.id.alert_message)
        resultLevel.text=result.toString() + "/100%"

        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)


        myDialog.setCancelable(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
        result=0
        val yesbtn = dialogBinding.findViewById<Button>(R.id.alert_yes)
        yesbtn.setOnClickListener {
            //myDialog.dismiss()
            finish()
        }
    }
}
