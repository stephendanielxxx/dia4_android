package com.dia.androidlearndia.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dia.androidlearndia.R
import com.dia.androidlearndia.databinding.ItemPokemonBinding

class PokemonAdapter(
    val pokemonList: ArrayList<PokemonModel>,
    val pokemonCallback: PokemonCallback): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val pokemonSelected = pokemonList[adapterPosition]
                pokemonCallback.onPokemonSelected(pokemonSelected)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        with(holder){
//            with(binding){
//                ivPokemon.setImageResource(R.drawable.pokemon)
//                tvName.text = pokemon.pokemonName
//            }
//            binding.ivPokemon.setImageResource(R.drawable.pokemon)
            Glide.with(binding.ivPokemon).load(pokemon.pokemonImage).into(binding.ivPokemon)
            binding.tvName.text = pokemon.pokemonName
            binding.tvDesc.text = pokemon.pokemonDesc
        }
    }

    interface PokemonCallback{
        fun onPokemonSelected(pokemonModel: PokemonModel)
    }
}