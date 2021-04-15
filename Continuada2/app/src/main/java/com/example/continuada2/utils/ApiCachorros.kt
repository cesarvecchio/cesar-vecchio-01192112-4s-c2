package com.example.continuada2.utils

import com.example.continuada2.model.Cachorro
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCachorros {


    @GET("cachorros")
    fun get(): List<Call<Cachorro>>

    @GET("cachorros/{id}")
    fun get(@Path("id") id: Int): Call<Cachorro>

}