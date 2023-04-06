package com.example.fluently

import android.R
import android.app.ProgressDialog
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
        binding= ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getImage.setOnClickListener{

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Fetching image....")
            progressDialog.setCancelable(false)
            progressDialog.show()


            val imageName = binding.etImageId.text.toString()
            val storageRef  = FirebaseStorage.getInstance().reference.child("images/$imageName.jpg")

            val localfile = File.createTempFile("tempImage", "jpg")
            storageRef.getFile(localfile).addOnSuccessListener {

                if(progressDialog.isShowing)
                    progressDialog.dismiss()


                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                binding.imageView.setImageBitmap(bitmap)


            }.addOnFailureListener{

                if(progressDialog.isShowing)
                    progressDialog.dismiss()

                Toast.makeText(this,"Failed to retrieve thr image",Toast.LENGTH_SHORT).show()

            }
        }


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
    fun youPressButton(@Suppress("UNUSED_PARAMETER")view : View){
        Toast.makeText(this, "you pressed button OK", Toast.LENGTH_SHORT).show()
    }
}