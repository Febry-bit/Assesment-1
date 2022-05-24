package org.d3if2015.konversisatuan.model

import org.d3if2015.konversisatuan.db.DrinkMeEntity

fun DrinkMeEntity.hitungDrinkMe(): HasilDrinkMe{
    val jumlahair = liter - 2
    val kategori = if (isAnak) {
        when {
            jumlahair < 0.0 -> KategoriDrinkMe.KURANG
            jumlahair >= 4.0 -> KategoriDrinkMe.LEBIH
            else -> KategoriDrinkMe.CUKUP
        }
    } else {
        when {
            jumlahair < 0.0 -> KategoriDrinkMe.KURANG
            jumlahair >= 5.0 -> KategoriDrinkMe.LEBIH
            else -> KategoriDrinkMe.CUKUP
        }
    }
    return HasilDrinkMe(jumlahair, kategori)
}