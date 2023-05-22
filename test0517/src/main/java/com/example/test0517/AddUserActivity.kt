package com.example.test0517

import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test0517.databinding.ActivityAddUserBinding
import java.io.BufferedInputStream
import java.io.File
import java.util.ArrayList
import java.util.Date

class AddUserActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddUserBinding
    lateinit var filePath:String
    var myDB: DBHelper? = null
    var id: EditText? = null
    var password: EditText? = null
    var email: EditText? = null
    var phone: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDB = DBHelper(this)

        binding.back1.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        binding.add.setOnClickListener {
            Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()


            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        var requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            try{
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data?.data!!)
                Log.d("lsy", "확인용 : ${inputStream?.read()}")
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)


                bitmap?.let {
                    binding.userImageView.setImageBitmap(bitmap)
                } ?:let {
                    Log.d("lsy", "비트맵 널")
                }

                inputStream?.close()
                inputStream = null
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.userImageView.setOnClickListener {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmsss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "G_${timeStamp}",
                ".jpeg",
                storageDir
            )
            filePath = file.absolutePath
            val photoUri: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test0517.fileprovider",
                file
            )

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)



        }

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {

            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmsss").format(Date())

            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "C_${timeStamp}",
                ".jpg",
                storageDir
            )

            filePath = file.absolutePath
            val photoUri: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test0517.fileprovider",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            requestCameraFileLauncher.launch(intent)
        }

    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try{

            var inputStream = contentResolver.openInputStream(fileUri)
            val bis = BufferedInputStream(inputStream)

            BitmapFactory.decodeStream(bis, null, options)
            bis!!.close()
            inputStream = null
        } catch (e: Exception){
            e.printStackTrace()
        }

        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if(height > reqHeight || width > reqWidth){
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while(halfHeight/inSampleSize >= reqHeight && halfWidth/inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}