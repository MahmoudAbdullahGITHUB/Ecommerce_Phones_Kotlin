package com.example.ecomer.UI

import com.example.ecomer.Model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("products")
    fun getProducts(@Query("limit") limit: String?): Call<List<ProductModel?>?>?
}
