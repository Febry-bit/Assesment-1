package org.d3if2015.konversisatuan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2015.konversisatuan.db.DrinkMeDao
import org.d3if2015.konversisatuan.db.DrinkMeEntity
import org.d3if2015.konversisatuan.model.HasilDrinkMe
import org.d3if2015.konversisatuan.model.KategoriDrinkMe
import org.d3if2015.konversisatuan.model.hitungDrinkMe

class HitungViewModel(private val db: DrinkMeDao) : ViewModel() {

    private val hasilDrinkMe = MutableLiveData<HasilDrinkMe?>()
    private val navigasi = MutableLiveData<KategoriDrinkMe?>()

    fun hitungJumlahAir(liter: Float, isAnak: Boolean) {

        val dataDrinkMe = DrinkMeEntity(
            liter = liter,
            isAnak = isAnak
        )
        hasilDrinkMe.value = dataDrinkMe.hitungDrinkMe()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataDrinkMe)
            }
        }
    }

    fun getHasilDrinkMe(): LiveData<HasilDrinkMe?> = hasilDrinkMe

    fun mulaiNavigasi() {
        navigasi.value = hasilDrinkMe.value?.kategori
    }
    fun selesaiNavigasi() {
        navigasi.value = null
    }
    fun getNavigasi() : LiveData<KategoriDrinkMe?> = navigasi
}