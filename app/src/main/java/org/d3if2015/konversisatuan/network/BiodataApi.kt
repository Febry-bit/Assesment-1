package org.d3if2015.konversisatuan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://febry-bit.github.io/apiku.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BiodataApi{
    @GET("biodata.json")
    suspend fun getPhotos() : List<Biodata>
}

object PhotoApi{
    val retrofitService : BiodataApi by lazy{
        retrofit.create(BiodataApi::class.java)
    }
}