package com.example.fluently

import android.R
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fluently.databinding.ActivityEasyLevelBinding
import com.example.fluently.databinding.ActivityLevelBinding
import com.google.android.material.transition.MaterialContainerTransform.ProgressThresholds
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class LevelActivity : AppCompatActivity() {
    lateinit var binding: ActivityLevelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageName = "m"

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
        }





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

    fun backToLevels(view: View){
        finish()
    }
    fun youPressButton(@Suppress("UNUSED_PARAMETER")view : View){
        Toast.makeText(this, "you pressed button OK", Toast.LENGTH_SHORT).show()
    }
}