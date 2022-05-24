package org.d3if2015.konversisatuan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DrinkMeDao {

    @Insert
    fun insert(jumlahair: DrinkMeEntity)

    @Query("SELECT * FROM jumlahair ORDER BY id DESC")
    fun getLastDrinkMe(): LiveData<List<DrinkMeEntity>>

    @Query("DELETE FROM jumlahair")
    fun clearData()

}