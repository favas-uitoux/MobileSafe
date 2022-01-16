package com.project.myapplication.apiservice

import com.project.myapplication.apiservice.responseModels.save_datas.ResponseModel1
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {


    @Multipart
    @POST("zpa/images/test.php")
    fun save_data_with_pics(
        @Part part:MultipartBody.Part?,
        @Part part2:MultipartBody.Part?,
        @Part("fname0") fname0: RequestBody?,
        @Part("na") name: RequestBody?,
        @Part("ad1") ad1: RequestBody?,
        @Part("ad2") ad2: RequestBody?,
        @Part("dist") dist: RequestBody?
    ): Call<ResponseModel1>





}