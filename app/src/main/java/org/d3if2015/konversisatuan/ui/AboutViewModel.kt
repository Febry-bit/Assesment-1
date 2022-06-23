package org.d3if2015.konversisatuan.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if2015.konversisatuan.network.Biodata
import org.d3if2015.konversisatuan.network.PhotoApi

enum class PictureApiStatus{ LOADING, ERROR, DONE}

class AboutViewModel : ViewModel() {

    private val _status = MutableLiveData<PictureApiStatus>()
    private val _photos = MutableLiveData<List<Biodata>>()

    val status: LiveData<PictureApiStatus> = _status
    val photos: LiveData<List<Biodata>> = _photos

    init {
        getPhoto()
    }
    private fun getPhoto() {
        viewModelScope.launch {
            _status.value = PictureApiStatus.LOADING
            try {
                _photos.value = PhotoApi.retrofitService.getPhotos()
                _status.value = PictureApiStatus.DONE
            }catch (e: Exception){
                _status.value = PictureApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }
}