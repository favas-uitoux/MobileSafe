package com.project.myapplication.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.project.myapplication.R


class SplashActivity:AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed({ checkPermission() }, 2000)

    }


        private fun checkPermission() {

            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.CAMERA
                ) + ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) + ActivityCompat.checkSelfPermission(
                    applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {


                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@SplashActivity, Manifest.permission.CAMERA
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@SplashActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        this@SplashActivity, Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {

                    requestPermission()


                } else {
                    requestPermission()
                }
            } else {
                goto_next_screen()
            }
        }


    private fun requestPermission() {
        val builder = AlertDialog.Builder(this@SplashActivity)
        builder.setCancelable(false)
        builder.setMessage(
            " Read and Write External" +
                    " Storage permissions are required to do the task."
        )
        builder.setTitle("Please grant these permissions")
        builder.setPositiveButton(
            "OK"
        ) { dialogInterface, i ->
            ActivityCompat.requestPermissions(
                this@SplashActivity, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE

                    ),
                MY_PERMISSIONS_REQUEST_CODE
            )
        }


        builder.setNeutralButton(
            "Cancel"
        ) { dialogInterface, i ->
            // lmain.setVisibility(View.GONE);
        }


        val dialog = builder.create()
        dialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CODE -> {

                // When request is cancelled, the results array are empty
                if (grantResults.size > 0 &&
                    grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    // Permissions are granted

                    Toast.makeText(applicationContext,"Granted", Toast.LENGTH_LONG).show()
                    goto_next_screen()
                } else {

                    // Permissions are denied

                    Toast.makeText(applicationContext,"Denied", Toast.LENGTH_LONG).show()

                }

            }

        }


    }


    private fun goto_next_screen()
    {

      //  Toast.makeText(applicationContext,"next screen", Toast.LENGTH_LONG).show()
        startActivity(Intent(this@SplashActivity, SaleActivity::class.java))
//        //customType(this@SplashActivity, "fadein-to-fadeout")
        finish()

    }




}