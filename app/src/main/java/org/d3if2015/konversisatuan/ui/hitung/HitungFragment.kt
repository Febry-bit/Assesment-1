package org.d3if2015.konversisatuan.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.databinding.FragmentHitungBinding
import org.d3if2015.konversisatuan.db.DrinkMeDb
import org.d3if2015.konversisatuan.model.HasilDrinkMe
import org.d3if2015.konversisatuan.model.KategoriDrinkMe

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = DrinkMeDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {
            hitungDrinkMe()
        }

        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilDrinkMe().observe(requireActivity(), { showResult(it) })

        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections
                    .actionHitungFragmentToSaranFragment(it)
            )
            viewModel.selesaiNavigasi()
        })
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val golongan = if (selectedId == R.id.anakRadioButton)
            getString(R.string.anak)
        else
            getString(R.string.dewasa)

        val message = getString(
            R.string.bagikan_template,
            binding.literEditText.text,
            golongan,
            binding.jumlahairTextView.text,
            binding.kategoriTextView.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun hitungDrinkMe(liter: Float, isAnak: Boolean): HasilDrinkMe {
        val jumlahair = liter - 2
        val kategori = getKategori(jumlahair, isAnak)
        return HasilDrinkMe(jumlahair, kategori)
    }

    private fun showResult(result: HasilDrinkMe?) {
        if (result == null) return
        if (result.jumlahair < 0){
            binding.jumlahairTextView.text = getString(R.string.jumlahair_x_kurang, result.jumlahair * -1)
            binding.kategoriTextView.text = getString(
                R.string.kategoridrinkme_x,
                getKategoriLabel(result.kategori)
            )
        } else{
            binding.jumlahairTextView.text = getString(R.string.jumlahair_x_lebih, result.jumlahair)
            binding.kategoriTextView.text = getString(
                R.string.kategoridrinkme_x,
                getKategoriLabel(result.kategori)
            )
        }

        binding.buttonGroup.visibility = View.VISIBLE
    }


    private fun hitungDrinkMe() {
        val liter = binding.literEditText.text.toString()
        if (TextUtils.isEmpty(liter)) {
            Toast.makeText(context, R.string.jumlahair_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.golongan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungJumlahAir(
            liter.toFloat(),
            selectedId == R.id.anakRadioButton
        )
    }

    private fun getKategori(jumlahair: Float, isAnak: Boolean): KategoriDrinkMe {
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
        return kategori
    }

    private fun getKategoriLabel(kategori: KategoriDrinkMe): String {
        val stringRes = when (kategori) {
            KategoriDrinkMe.KURANG -> R.string.kurang
            KategoriDrinkMe.LEBIH -> R.string.lebih
            KategoriDrinkMe.CUKUP -> R.string.cukup
        }
        return getString(stringRes)
    }
}