package com.svape.movieapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.svape.movieapp.R
import com.svape.movieapp.databinding.FragmentDetailMovieBinding


class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private lateinit var binding: FragmentDetailMovieBinding

    //get SafeArguments
    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailMovieBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}")
            .centerCrop().into(binding.imageMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}")
            .centerCrop().into(binding.imageBackground)

        binding.txtExplanation.text = args.overview
        binding.txtTitle.text = args.title
        binding.nameAuthor.text = "Language: ${args.language}"
        binding.date.text = args.releaseDate
    }

}