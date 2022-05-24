package org.d3if2015.konversisatuan.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2015.konversisatuan.db.DrinkMeDao

class HistoriViewModel(private val db: DrinkMeDao) : ViewModel() {
    val data = db.getLastDrinkMe()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}