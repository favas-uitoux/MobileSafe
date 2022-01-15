package com.project.myapplication.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import com.project.myapplication.BuildConfig
import com.project.myapplication.R
import com.theartofdev.edmodo.cropper.CropImage
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class SaleActivity:BaseActivity(), View.OnClickListener {

    val TAKE_PIC = 300
    var selected=""
    lateinit var imgAvatar:ImageView
    lateinit var pic0:ImageView
    lateinit var pic1:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)



        init();
        setOnClicks()

    }

    override fun onClick(v: View?) {

        if(v!!.id==R.id.imgAvatar)
        {
            selected="profile"
        }
        else if(v!!.id==R.id.pic0)
        {
            selected="pic0"
        }
       else  if(v!!.id==R.id.pic1)
        {
            selected="pic1"
        }

        showPopup()

    }


    private fun showPopup()
    {
        var dialog: Dialog = Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup1);
        dialog.show()

        var cardCamera = dialog.findViewById<CardView>(R.id.cardCamera)
        var cardGallery = dialog.findViewById<CardView>(R.id.cardGallery)

        cardCamera.setOnClickListener {

            startCamera()
        }

        cardGallery.setOnClickListener {

            startGallery()
        }


    }


    private fun startCamera()
    {

        try {
            var takepicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takepicIntent.resolveActivity(getPackageManager())
            var photofile = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "100.jpg"
            )

            var furi = FileProvider.getUriForFile(
                Objects.requireNonNull(applicationContext),
                BuildConfig.APPLICATION_ID + ".provider",
                photofile
            )

            takepicIntent.putExtra(MediaStore.EXTRA_OUTPUT, furi)
            startActivityForResult(takepicIntent, TAKE_PIC)
        }
        catch (e:Exception)
        {}




    }

    private fun startGallery()
    {
        try {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(intent, "Select Picture"), CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE)
        }
        catch (e:Exception)
        {}

    }




    private fun init()
    {
        imgAvatar=findViewById(R.id.imgAvatar)
        pic0=findViewById(R.id.pic0)
        pic1=findViewById(R.id.pic1)
    }

    private fun setOnClicks()
    {
        imgAvatar!!.setOnClickListener(this)
        pic0!!.setOnClickListener(this)
        pic1!!.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try{

            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
                var res: Uri = CropImage.getPickImageResultUri(this, data);
                startcrop(res,selected+".jpg")


            }
            else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                var res = data?.let { UCrop.getOutput(it) }


             if(selected.equals("profile"))
             {
                 imgAvatar.setImageURI(null)
                 imgAvatar.setImageURI(res)
             }
              else if(selected.equals("pic0"))
             {
                 pic0.setImageURI(null)
                 pic0.setImageURI(res)

             }
             else if(selected.equals("pic1"))
             {
                 pic1.setImageURI(null)
                 pic1.setImageURI(res)

             }




            }
            else if (requestCode == TAKE_PIC && resultCode == RESULT_OK) {

                try {
                    var photofile =
                        File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "100.jpg")

                    var furi : Uri = FileProvider.getUriForFile(
                        Objects.requireNonNull(this),
                        BuildConfig.APPLICATION_ID.toString() + ".provider",
                        photofile)

                    if (furi != null)
                    {

                        // Uri res = CropImage.getPickImageResultUri(this, data);
                        startcrop(furi,selected+".jpg")
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