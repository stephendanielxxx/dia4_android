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
import com.dia.androidlearndia.databinding.FragmentFavoriteBinding
import com.dia.androidlearndia.rv.PokemonAdapter
import com.dia.androidlearndia.rv.PokemonModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment(), PokemonAdapter.PokemonCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    private val viewModel: FavoriteViewModel by viewModels()
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPokemon.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        //set dummy data
        val pokemonList = arrayListOf<PokemonModel>()
        pokemonList.add(PokemonModel(1, "Pikachu", "This is pikachu",
            "https://static.wikia.nocookie.net/pokemon/images/f/f4/020Raticate.png/revision/latest/scale-to-width-down/350?cb=20140328192215"))
        pokemonList.add(PokemonModel(2, "Bulbasaur", "This is Bulbasaur",
            "https://static.wikia.nocookie.net/pokemon/images/2/21/001Bulbasaur.png/revision/latest/smart/width/250/height/250?cb=20200620223551"))
        pokemonList.add(PokemonModel(3, "Squirtle", "This is Squirtle",
            "https://static.wikia.nocookie.net/pokemon/images/3/39/007Squirtle.png/revision/latest?cb=20200620223922"))
        pokemonList.add(PokemonModel(4, "Psyduck", "This is Psyduck",
            "https://static.wikia.nocookie.net/pokemon/images/5/57/018Pidgeot.png/revision/latest?cb=20140328192120"))
        pokemonList.add(PokemonModel(5, "Charizard", "This is Charizard",
            "https://static.wikia.nocookie.net/pokemon/images/7/73/004Charmander.png/revision/latest/scale-to-width-down/1000?cb=20200620223744"))

        pokemonAdapter = PokemonAdapter(pokemonList, this)
        binding.rvPokemon.adapter = pokemonAdapter

        setObserver()

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            getErrorView()
        }

        val test = "email@gmail.com"
        val isValid = test.contains("@")
        val string = resources.getString(R.string.favorite)
        Log.i("logger","is valid $isValid, string = $string")
    }

    suspend fun getErrorView(){

    }

    private fun setObserver() {
        viewModel.pokemonStatus.observe(viewLifecycleOwner){
            Log.i("Logger", "Pokemon status $it")
            Toast.makeText(requireContext(), "Pokemon status $it", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onPokemonSelected(pokemonModel: PokemonModel) {
        Log.i("Logger", "Pokemon selected $pokemonModel")
        viewModel.changePokemonStatus(pokemonModel.pokemonName)
    }
}