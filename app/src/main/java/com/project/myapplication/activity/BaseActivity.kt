package com.project.myapplication.activity

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.yalantis.ucrop.UCrop
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

open class BaseActivity:AppCompatActivity() {



public fun saveReceivedImage(bitmap:Bitmap,imageName:String)
{

    try {
        var path = File(applicationContext.filesDir, File.separator)
        //     File path = new File(getApplicationContext().getFilesDir(), "Recycler" + File.separator + "Images");
        if (!path.exists()) {
            path.mkdirs()
        }

        var outFile = File(path, imageName+".jpg")
        var outputStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.close()
    }
    catch (e:FileNotFoundException)
    {}
    catch (e:IOException)
    {

    }


}




    protected fun startcrop(uri : Uri,fname:String)
    {

        try {

           var destn = fname
            var uCrop = UCrop.of(uri, Uri.fromFile(File(cacheDir, destn)))
            uCrop.withAspectRatio(1f, 1f)
            uCrop.withMaxResultSize(350, 350)
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