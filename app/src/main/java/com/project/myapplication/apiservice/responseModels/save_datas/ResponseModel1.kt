package com.project.myapplication.apiservice.responseModels.save_datas

import com.google.gson.annotations.SerializedName

data class ResponseModel1(



	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("slno")
val slno: String? = null,

	@field:SerializedName("result")
	val result: String? = null

)
