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
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fluently.databinding.ActivityLevelBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.checkerframework.checker.units.qual.s
import java.io.File

//11111


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
        var id = "";
        getDataAct(language, difficult, level, npp)
        binding.btn.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.my_custom_dialog,null)

            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()




        }











        /*val storageRef  = FirebaseStorage.getInstance().reference.child("images/easy/10/$imageName.jpg")
        val localfile = File.createTempFile("tempImage", "jpg")//створюєм тимчасовий файл

        val progressDialog = ProgressDialog(this)//вивід текту поки чекаємо картинку
        progressDialog.setMessage("Fetching image....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        storageRef.getFile(localfile).addOnSuccessListener{
            if(progressDialog.isShowing)//якщо вікно є то воно закривається
                progressDialog.dismiss()


            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)//перетворює файл у зображення
            binding.imageView.setImageBitmap(bitmap)//отриманий об'єкт Bitmap встановлюється в якості зображення в ImageView

        }.addOnFailureListener{//якщо ні то

            if(progressDialog.isShowing)//якщо вікно є то воно закривається
                progressDialog.dismiss()

            Toast.makeText(this,"Failed to retrieve thr image",Toast.LENGTH_SHORT).show()
            //вивід на екран повідомлення що в нас помилка


        }




        binding.getImage.setOnClickListener{
           if(binding.etImageId.text.toString() == "m"){
               val imageName = "w"
               val storageRef  = FirebaseStorage.getInstance().reference.child("images/easy/10/$imageName.jpg")
               val localfile = File.createTempFile("tempImage", "jpg")//створюєм тимчасовий файл

               val progressDialog = ProgressDialog(this)//вивід текту поки чекаємо картинку
               progressDialog.setMessage("Fetching image....")
               progressDialog.setCancelable(false)
               progressDialog.show()

               storageRef.getFile(localfile).addOnSuccessListener{
                   if(progressDialog.isShowing)//якщо вікно є то воно закривається
                       progressDialog.dismiss()


                   val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)//перетворює файл у зображення
                   binding.imageView.setImageBitmap(bitmap)//отриманий об'єкт Bitmap встановлюється в якості зображення в ImageView

               }.addOnFailureListener{//якщо ні то

                   if(progressDialog.isShowing)//якщо вікно є то воно закривається
                       progressDialog.dismiss()

                   Toast.makeText(this,"Failed to retrieve thr image",Toast.LENGTH_SHORT).show()
                   //вивід на екран повідомлення що в нас помилка


               }
           }
            else{
               Toast.makeText(this,"Wrong word",Toast.LENGTH_SHORT).show()
           }
        }*/



        }


    /*fun getData(view : View) {
       db.collection("hard").document("level").collection("7_level")
            .get()
            .addOnSuccessListener{
                for(documentSnapshot : DocumentSnapshot in it.documents) {
                    if(documentSnapshot.id=="n_p_p"){
                    val surname = documentSnapshot.getString("en").toString()
                    val s = documentSnapshot.getString("id").toString()
                    val name = documentSnapshot.getString("ger").toString()
                    val patronymic = documentSnapshot.getString("pl").toString()
                    txt = "$surname $name $patronymic $s"
                        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener {
                Log.w("TAG", "Error getting documents: ")
            }
    }*/

    fun getDataAct(language:String, difficult:String, level:String, npp:Int ) {
        var txt="11"
        var en = ""
        var id = ""
        //val myString: String = "Це мій текст"String
        var ger = ""
        var pl = ""
        db.collection(difficult).document("level").collection(level)
            .get()
            .addOnSuccessListener {
                Toast.makeText(this, "before for", Toast.LENGTH_SHORT).show()
                for (documentSnapshot: DocumentSnapshot in it.documents) {

                    if (documentSnapshot.id == npp.toString()) {
                        en = documentSnapshot.getString("en").toString()
                        id = documentSnapshot.getString("id").toString()
                        ger = documentSnapshot.getString("ger").toString()
                        pl = documentSnapshot.getString("pl").toString()
                        txt = "$id $en $ger $pl "
                        Toast.makeText(this, language, Toast.LENGTH_SHORT).show()
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


                        /*binding.WomanLevel.setOnClickListener {
                            when (language) {
                                "en" -> {hint(en)}
                                "pl" -> {hint(pl)}
                                "ger" -> {hint(ger)}
                            }
                        }*/


                        binding.getImage.setOnClickListener {
                            /*if (binding.etImageId.text.toString() == pl) {
                                var npp = npp + 1
                                if (npp == 11) {
                                    finish()//треба викликати backtolevels
                                }
                                getDataAct(language, difficult, level, npp)
                            }*/

                            when (language) {
                                "en" -> {
                                    if(binding.etImageId.text.toString() == en) {
                                        var npp = npp + 1
                                        if (npp == 11) {
                                            finish()//треба викликати backtolevels
                                        }
                                        getDataAct(language, difficult, level, npp)
                                    }
                                }
                                "ger" -> {
                                    if(binding.etImageId.text.toString() == ger) {
                                        var npp = npp + 1
                                        if (npp == 11) {
                                            finish()//треба викликати backtolevels
                                        }
                                        getDataAct(language, difficult, level, npp)
                                    }
                                }
                                "pl" -> {
                                    if(binding.etImageId.text.toString() == pl) {
                                        var npp = npp + 1
                                        if (npp == 11) {
                                            finish()//треба викликати backtolevels
                                        }
                                        getDataAct(language, difficult, level, npp)
                                    }
                                }
                                else -> Toast.makeText(this, "Wrong language", Toast.LENGTH_SHORT).show()
                            }






                            /*if(language==pl) {
                                if (binding.etImageId.text.toString() == pl) {
                                    var npp = npp + 1
                                    if (npp == 11) {
                                        finish()//треба викликати backtolevels
                                    }
                                    getDataAct(language, difficult, level, npp)
                                }
                                else Toast.makeText(this, "Wrong word", Toast.LENGTH_SHORT).show()
                            }
                            if(language==ger) {
                                if (binding.etImageId.text.toString() == ger) {
                                    var npp = npp + 1
                                    if (npp == 11) {
                                        finish()//треба викликати backtolevels
                                    }
                                    getDataAct(language, difficult, level, npp)
                                }
                                else Toast.makeText(this, "Wrong word", Toast.LENGTH_SHORT).show()
                            }
                            if(language==en) {
                                if (binding.etImageId.text.toString() == en) {
                                    var npp = npp + 1
                                    if (npp == 11) {
                                        finish()//треба викликати backtolevels
                                    }
                                    getDataAct(language, difficult, level, npp)
                                }
                                else Toast.makeText(this, "Wrong word", Toast.LENGTH_SHORT).show()
                            }*/


                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.w("TAG", "Error getting documents: ")
            }

        /*Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
        val storageRef = FirebaseStorage.getInstance().reference.child("images/easy/1/$id.jpg")
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


        }*/

        /*binding.getImage.setOnClickListener {
            if (binding.etImageId.text.toString() == "en") {
                var npp = npp + 1
                if (npp == 11) {
                    finish()//треба викликати backtolevels
                }
                getDataAct(language, difficult, level, npp)
            }
        }*/


    }






        /*new*/
        /*intent.extras?.getString(Const.LANGUAGE)
        intent.extras?.getString(Const.DIFFICULT)
        intent.extras?.getInt(Const.LEVEL)
        when (intent.extras?.getString(Const.LANGUAGE)) {
            "eng" -> {
                Toast.makeText(this, "eng", Toast.LENGTH_SHORT).show()
            }
            "deutsch" -> {
                Toast.makeText(this, "deutsch", Toast.LENGTH_SHORT).show()
            }*/
                /*---new----*/













        /*
        binding.getImage.setOnClickListener{//чекаємо на натискання кнопки

            val progressDialog = ProgressDialog(this)//вивід текту поки чекаємо картинку
            progressDialog.setMessage("Fetching image....")
            progressDialog.setCancelable(false)
            progressDialog.show()


            val imageName = binding.etImageId.text.toString() // текст з поля записуєм у змінну
            val storageRef  = FirebaseStorage.getInstance().reference.child("images/$imageName.jpg") //у змінну записуєм шлях до картинки у бд

            val localfile = File.createTempFile("tempImage", "jpg")//створюєм тимчасовий файл
            storageRef.getFile(localfile).addOnSuccessListener{
            //завантажує файл з бд у локал файл
            //якщо завантаження завершиться успішно виконається код

                if(progressDialog.isShowing)//якщо вікно є то воно закривається
                    progressDialog.dismiss()


                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)//перетворює файл у зображення
                binding.imageView.setImageBitmap(bitmap)//отриманий об'єкт Bitmap встановлюється в якості зображення в ImageView

            }.addOnFailureListener{//якщо ні то

                if(progressDialog.isShowing)//якщо вікно є то воно закривається
                    progressDialog.dismiss()

                Toast.makeText(this,"Failed to retrieve thr image",Toast.LENGTH_SHORT).show()
                //вивід на екран повідомлення що в нас помилка

            }
        }


        //Toast.makeText(this, intent.extras?.getString("Lang").toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getString(Const.LANGUAGE), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getString(Const.DIFFICULT), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, intent.extras?.getInt(Const.LEVEL).toString(), Toast.LENGTH_SHORT).show()
    }
    */

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


    fun openImage(id : String, difficult: String, level: String)
    {
        binding.etImageId.text.clear()
        Toast.makeText(this, "inside open", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
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
}
