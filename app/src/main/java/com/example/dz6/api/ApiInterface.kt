package com.example.dz6.api

import com.example.dz6.api.models.CategoriesApiModel
import com.example.dz6.api.models.ProductsApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("insertCategory.php")
    fun insertCategory(
        @Field("name") name: String?  // должны быть идентичны полям ид и нэйм на сервере и в категори апи модел
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("insertProduct.php")
    fun insertProduct(
        @Field("name") name: String?,
        @Field("category") category: String?,
        @Field("price") price: String?
    ): Call<ResponseBody?>?


    @FormUrlEncoded
    @POST("updateCategory.php")
    fun updateCategory(
        @Field("id") id: Int,
        @Field("name") name: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("updateProduct.php")
    fun updateProduct(
        @Field("id") id: Int,
        @Field("name") name: String?,
        @Field("category") category: String?,
        @Field("price") price: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("deleteCategory.php")
    fun deleteCategory(
        @Field("id") id: Int
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("deleteProduct.php")
    fun deleteProduct(
        @Field("id") id: Int
    ): Call<ResponseBody?>?

    @DELETE("clearCategories.php")
    fun clearCategories(): Call<ResponseBody?>?

    @DELETE("clearProducts.php")
    fun clearProducts(): Call<ResponseBody?>?

    @GET("getCategories.php")
    fun getCategory(): Call<ArrayList<CategoriesApiModel>>  // указываем что данные хотим из БД получать в формате модели категориапимодел

    @GET("getProducts.php")
    fun getProduct(): Call<ArrayList<ProductsApiModel>>

    @GET("getFilter.php")
    fun getFilter(@Query("category") category: String, @Query("price") price: String):
            Call<ArrayList<ProductsApiModel>>

}