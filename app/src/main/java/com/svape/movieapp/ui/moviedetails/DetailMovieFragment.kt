package com.svape.movieapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.svape.movieapp.R
import com.svape.movieapp.databinding.FragmentDetailMovieBinding


class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private lateinit var binding: FragmentDetailMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailMovieBinding.bind(view)
    }

}