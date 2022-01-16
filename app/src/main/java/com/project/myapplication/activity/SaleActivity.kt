package com.project.myapplication.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import com.project.myapplication.BuildConfig
import com.project.myapplication.R
import com.project.myapplication.apiservice.ApiClient
import com.project.myapplication.apiservice.Endpoint
import com.project.myapplication.apiservice.responseModels.save_datas.ResponseModel1
import com.theartofdev.edmodo.cropper.CropImage
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File
import java.util.*

class SaleActivity:BaseActivity(), View.OnClickListener {

    val TAKE_PIC = 300
    var selected=""
    lateinit var imgAvatar:ImageView
    lateinit var pic0:ImageView
    lateinit var pic1:ImageView
    lateinit var cl_submit:ConstraintLayout
    lateinit var pb1:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)



        init();
        setOnClicks()

        cl_submit.setOnClickListener { upload() }

    }


    private fun upload()
    {

        pb1.visibility=View.VISIBLE

      var  filepath = getFilesDir().toString() + "/" + "pic0" + ".jpg";
        var file = File(filepath)

        var  filepath1 = getFilesDir().toString() + "/" + "pic1" + ".jpg";
        var file1 = File(filepath1)

        var requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file);
        var part = MultipartBody.Part.createFormData("bill", file.getName(), requestBody);

        var requestBody1 = RequestBody.create("image/*".toMediaTypeOrNull(), file1);
        var part1 = MultipartBody.Part.createFormData("bill1", file1.getName(), requestBody1);

        var by = RequestBody.create("text/plain".toMediaTypeOrNull(), "favas");
        var fname0 = RequestBody.create("text/plain".toMediaTypeOrNull(), "pic0.jpg");



        val apiService = ApiClient.getClient(Endpoint::class.java)
        var call = apiService.save_data_with_pics(part,part1,fname0, by, by, by,by)





        call!!.enqueue(object :
            Callback<ResponseModel1> {
            override fun onResponse(
                call: Call<ResponseModel1>,
                response: retrofit2.Response<ResponseModel1>
            ) {
                pb1.visibility=View.INVISIBLE
                Log.d("result","ok")
                if (response.body()?.message.equals("ok")) {

                }
                else
                {

                }


            }

            override fun onFailure(
                call: Call<ResponseModel1>,
                t: Throwable
            ) {
                pb1.visibility=View.INVISIBLE
                Log.d("result","failed")

            }
        })


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
        cl_submit=findViewById(R.id.cl_submit)
        pb1=findViewById(R.id.pb1)

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


                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, res)

             if(selected.equals("profile"))
             {
                 saveReceivedImage(bitmap, "profile")
                 imgAvatar.setImageURI(null)
                 imgAvatar.setImageURI(res)


             }
              else if(selected.equals("pic0"))
             {
                 saveReceivedImage(bitmap, "pic0")
                 pic0.setImageURI(null)
                 pic0.setImageURI(res)

             }
             else if(selected.equals("pic1"))
             {
                 saveReceivedImage(bitmap, "pic1")
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