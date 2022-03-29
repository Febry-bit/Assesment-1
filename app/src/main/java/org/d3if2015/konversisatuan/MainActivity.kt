package org.d3if2015.konversisatuan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if2015.konversisatuan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Tesss
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKonversi.setOnClickListener { konversiSatuan() }
    }
    @SuppressLint("StringFormatMatches")
    private fun konversiSatuan() {
        //Log.d("MainActivity", "Tombol diklik!")

        val konversi = binding.satuanInp.text.toString()

        if (TextUtils.isEmpty(konversi)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val gram = konversi.toFloat() * 1000.0
        binding.gramTextView.text = getString(R.string.gram_x, gram)
        val onsSatuan = konversi.toFloat() * 10.0
        binding.onsTextView.text = getString(R.string.ons_x, onsSatuan)
    }
}