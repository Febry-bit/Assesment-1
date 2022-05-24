package org.d3if2015.konversisatuan.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jumlahair")
data class DrinkMeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var liter: Float,
    var isAnak: Boolean
)