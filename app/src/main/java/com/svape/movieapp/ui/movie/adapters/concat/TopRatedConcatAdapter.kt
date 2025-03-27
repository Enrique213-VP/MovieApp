package com.svape.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svape.movieapp.core.BaseConcatHolder
import com.svape.movieapp.databinding.TopRatedMoviesRowBinding
import com.svape.movieapp.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter (private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(movieAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder (val binding: TopRatedMoviesRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRated.adapter = adapter
        }
    }
}