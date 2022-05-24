package org.d3if2015.konversisatuan.ui

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.databinding.FragmentSaranBinding
import org.d3if2015.konversisatuan.model.KategoriDrinkMe


class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI(kategori: KategoriDrinkMe) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriDrinkMe.KURANG -> {
                actionBar?.title = getString(R.string.judul_kurang)
                binding.imageView.setImageResource(R.drawable.kurang)
                binding.textView.text = getString(R.string.saran_kurang)
            }
            KategoriDrinkMe.LEBIH -> {
                actionBar?.title = getString(R.string.judul_lebih)
                binding.imageView.setImageResource(R.drawable.lebih)
                binding.textView.text = getString(R.string.saran_lebih)
            }
            KategoriDrinkMe.CUKUP -> {
                actionBar?.title = getString(R.string.judul_cukup)
                binding.imageView.setImageResource(R.drawable.cukup)
                binding.textView.text = getString(R.string.saran_cukup)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}