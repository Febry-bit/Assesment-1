package org.d3if2015.konversisatuan.ui

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.network.Biodata

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.animasi_loading)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Biodata>?){
    val adapter = recyclerView.adapter as SaranGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: PictureApiStatus?){
    when(status){
        PictureApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.animasi_loading)
        }
        PictureApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_conn_error)
        }
        PictureApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}