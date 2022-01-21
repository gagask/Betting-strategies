package com.example.bettingstrategies.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bettingstrategies.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val selectedStrategy = DetailFragmentArgs.fromBundle(requireArguments()).selectedStrategy

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.strategy = selectedStrategy

        return binding.root
    }

}