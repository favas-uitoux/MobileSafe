package com.project.myapplication.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.yalantis.ucrop.UCrop
import java.io.File

open class BaseActivity:AppCompatActivity() {


    protected fun startcrop(uri : Uri,fname:String)
    {

        try {
//            var destn = "profile2.jpg";
           var destn = fname
            var uCrop = UCrop.of(uri, Uri.fromFile(File(cacheDir, destn)))
            uCrop.withAspectRatio(1f, 1f)
            uCrop.withMaxResultSize(750, 750)
            uCrop.withOptions(getoptions())
            uCrop.start(this@BaseActivity)
        }
        catch (e:Exception)
        {}


    }

    protected fun getoptions():UCrop.Options
    {
        var opt = UCrop.Options()
        opt.setCompressionQuality(100)
        //  opt.setMaxBitmapSize(100000);
        //  opt.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        //  opt.setMaxBitmapSize(100000);
        //  opt.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        opt.setHideBottomControls(false)
        opt.setFreeStyleCropEnabled(true)
        return opt
    }



}