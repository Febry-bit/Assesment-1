package org.d3if2015.konversisatuan.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.databinding.ItemHistoriBinding
import org.d3if2015.konversisatuan.db.DrinkMeEntity
import org.d3if2015.konversisatuan.model.KategoriDrinkMe
import org.d3if2015.konversisatuan.model.hitungDrinkMe
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<DrinkMeEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DrinkMeEntity>() {
                override fun areItemsTheSame(
                    oldData: DrinkMeEntity, newData: DrinkMeEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: DrinkMeEntity, newData: DrinkMeEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID"))

        fun bind(item: DrinkMeEntity) = with(binding) {
            val hasilJumlahAir = item.hitungDrinkMe()
            kategoriTextView.text = hasilJumlahAir.kategori.toString().substring(0, 1)
            val colorRes = when (hasilJumlahAir.kategori) {
                KategoriDrinkMe.KURANG -> R.color.kurang
                KategoriDrinkMe.LEBIH -> R.color.lebih
                else -> R.color.cukup
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            jumlahairTextView.text = root.context.getString(
                R.string.hasil_drinkme,
                hasilJumlahAir.jumlahair, hasilJumlahAir.kategori.toString()
            )
            val golongan = root.context.getString(
                if (item.isAnak) R.string.anak else R.string.dewasa
            )
            dataTextView.text = root.context.getString(
                R.string.data_drinkme,
                item.liter, golongan
            )
        }
    }
}