package com.dia.androidlearndia.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dia.androidlearndia.R
import com.dia.androidlearndia.databinding.FragmentHomeBinding
import com.dia.androidlearndia.rv.PokemonAdapter
import com.dia.androidlearndia.rv.PokemonModel
import com.dia.androidlearndia.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), PokemonAdapter.PokemonCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        binding.rvPokemon.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btnSave.setOnClickListener {
            val pokemonName = binding.etPokemonName.text.toString()
            val pokemonDesc = binding.etPokemonDesc.text.toString()
            val pokemonColor = binding.etPokemonColor.text.toString()

            viewModel.savePokemon(pokemonName, pokemonDesc, pokemonColor)

        }

        viewModel.getAllPokemon()
        viewModel.getDataStoreValue()
    }

    private fun setObserver() {
        viewModel.pokemonModel.observe(viewLifecycleOwner){
            val pokemonModels = it as ArrayList<PokemonModel>
            adapter = PokemonAdapter(pokemonModels, this)

            binding.rvPokemon.adapter = adapter
        }

        viewModel.stringData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "String data = $it", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onPokemonSelected(pokemonModel: PokemonModel) {
        Log.i("logger", "$pokemonModel")
        viewModel.setDataStoreValue(pokemonModel.pokemonName)
    }
}