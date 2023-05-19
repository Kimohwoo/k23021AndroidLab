package com.example.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test16.databinding.ActivityMain2Binding
import java.io.BufferedInputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var filePath: String

    // 뷰는 재활용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //순서2
        //카메라 앱 촬영 후 사진 재활용
        //앱별 저장소(Pictures에 파일로 저장 작업)
        //camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(
                // 저장할 물리 파일의 위치를 Uri 타입으로 변경
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        //갤러리 앱에서 사진 선택 후, 후처리 하기
        //gallery request launcher..................
        //갤러리 앱에서 사진 선택 한 데이터를 여기서 처리 합니다.
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            //it -> 사진 데이터
            try {
                // 결괏값은 Int인데 예로
                // option.inSampleSize = calRatio -> 의미가 1/4로 줄인다
                // 특정값의 비율을 임의로 정해도 되지만, 특정 사이즈를 요구한 값이 있으면
                // 그 값에 맞추는 함수
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    // 정적 res -> values -> dimens.xml 특정 크기를 정해서, 재사용 했다
                    // 그냥 하드 코딩으로 150dp 주어도 상관없음
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    //원하는 사진을 내가 원하는 크기에 맞게 할당
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("lsy", "bitmap null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //시작, 이벤트 발생
        binding.galleryButton.setOnClickListener {
            //gallery app........................
            //Intent.ACTION_PICK -> 갤러리 앱 호출, 두번째 매개변수의 타입을 보고 판단
            //두번째 매개변수의 타입을 보고 판단 : MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //속성의 값은 모든 이미지
            intent.type = "image/*"
            //후처리 작업 시작, 현재 액티비티 -> 갤러리 앱 : 사진 선택 -> 현재 액티비티 돌아옴
            // 위에 정의된 requestGalleryLauncher.launch로 감
            requestGalleryLauncher.launch(intent)
        }


        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            //현재 날짜와 시간을 조합해서 파일이름 만들어 줌
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 실제 저장되는 물리 경로
            // 앱별 저장소, getExternalFilesDir 이용하면
            // 개발자가 앱별 저장소에 접근하기 위해서 시스템에 요청, 콘텐츠 프로바이더를 이용함
            // 그 떄 사용할 경로도 설정을 같이 함
            // xml -> 경로 설정
            //DIRECTORY_PICTURES 상수, 즉 갤러리 위치에 물리 파일을 저장
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            // 실제 물리 파일의 위치 주소, 절대 경로
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test16.fileprovider",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //카메라 앱에 데이터를 전달, 키: 상수, output 문자열
            //val값은 : photoURI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }
    }

    //이미지의 크기를 적당히 조절하는 함수
    //현재 뷰의 가로 세로 150dp, 150dp
    //실제 사진의 해상도 : 3000dp, 2000dp -> 반으로 줄여 나감
    //1500, 1000 -> 750, 500 -> 375, 250 -> 187.5, 125

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        // 사진 처리 업무보다는 관련 옵션을 정한다
        val options = BitmapFactory.Options()
        //inJustDecodeBounds = true 본 업무 말고 옵션만 처리
        options.inJustDecodeBounds = true
        try {
            //contentResolver.openInputStream(fileUri) 선택된 사진을 바이트로 알아서 바이트로 반환
            //사진이 바이트로 읽어놓은 상태
            var inputStream = contentResolver.openInputStream(fileUri)
            val bufferStream = BufferedInputStream(inputStream)
            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(bufferStream, null, options)
            bufferStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        //읽은 사진의 가로 세로 정보를 재할당
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        //원본 사이즈 그대로
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}

