package com.project.myapplication.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.project.myapplication.BuildConfig
import com.project.myapplication.R
import com.theartofdev.edmodo.cropper.CropImage
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*


class MainActivity : BaseActivity() {

    val TAKE_PIC = 300
    lateinit var iv1:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();

        iv1.setOnClickListener {
            try {
//method1
//                var takepicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                takepicIntent.resolveActivity(getPackageManager())
//                var photofile = File(
//                    getExternalFilesDir(Environment.DIRECTORY_PICTURES),
//                    "100.jpg"
//                )
//
//                var furi = FileProvider.getUriForFile(
//                    Objects.requireNonNull(applicationContext),
//                    BuildConfig.APPLICATION_ID + ".provider",
//                    photofile
//                )
//
//                takepicIntent.putExtra(MediaStore.EXTRA_OUTPUT, furi)
//                startActivityForResult(takepicIntent, TAKE_PIC)


                // CropImage.startPickImageActivity(Objects.requireNonNull(this))
// method 2
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE)
            }
            catch (e:Exception)
            {
                Toast.makeText(applicationContext,"error",Toast.LENGTH_LONG).show()

            }

        }
    }
    private fun init()
    {
        iv1=findViewById(R.id.iv1);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try{

            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
                var res: Uri = CropImage.getPickImageResultUri(this, data);
               // startcrop(res)


            }
            else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                var res = data?.let { UCrop.getOutput(it) }


                // Toast.makeText(getActivity(),"ddsfd",Toast.LENGTH_LONG).show();
                iv1.setImageURI(null)
                iv1.setImageURI(res)
            }
            else if (requestCode == TAKE_PIC && resultCode == RESULT_OK) {

                try {
                    var photofile =
                        File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "100.jpg")

                    var furi :Uri= FileProvider.getUriForFile(
                        Objects.requireNonNull(this),
                        BuildConfig.APPLICATION_ID.toString() + ".provider",
                        photofile)

                        if (furi != null)
                        {

                            // Uri res = CropImage.getPickImageResultUri(this, data);
                        //    startcrop(furi)
                        }


                }
                catch (e:Exception)
                {
                    Toast.makeText(applicationContext, "not ", Toast.LENGTH_LONG).show()
                }



            }


        }
        catch (e:Exception)
        {}

    }

}