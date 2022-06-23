package org.d3if2015.konversisatuan.network

import com.squareup.moshi.Json

data class Biodata(
    val id: String,
    @Json(name = "gambar")
    val imgSrcUrl: String
)