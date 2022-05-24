package org.d3if2015.konversisatuan.ui.histori

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if2015.konversisatuan.R
import org.d3if2015.konversisatuan.databinding.FragmentHistoriBinding
import org.d3if2015.konversisatuan.db.DrinkMeDb

class HistoriFragment : Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val db = DrinkMeDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private  lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriBinding.inflate(layoutInflater,
            container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            //Log.d("HistoriFragment", "Jumlah data: ${it.size}")
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}