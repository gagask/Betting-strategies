package com.example.bettingstrategies.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bettingstrategies.MyViewModelFactory
import com.example.bettingstrategies.R
import com.example.bettingstrategies.StrategiesAdapter
import com.example.bettingstrategies.database.StrategyDatabase
import com.example.bettingstrategies.databinding.FragmentFavoriteBinding
import com.example.bettingstrategies.databinding.FragmentStrategiesBinding
import com.example.bettingstrategies.strategies.StrategiesFragmentDirections
import com.example.bettingstrategies.strategies.StrategiesViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = StrategyDatabase.getInstance(application).strategyDatabaseDao
        val viewModelFactory = MyViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
        binding.viewModel = viewModel

        binding.strategiesList.adapter = StrategiesAdapter(
            StrategiesAdapter.OnClickListener {
                viewModel.displayDetails(it)
            },
            StrategiesAdapter.OnClickListener {
                viewModel.updateFavoritesDB(it)
            })

        viewModel.navigateToSelectedStrategy.observe(viewLifecycleOwner){
            if ( null != it ) {
                findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        return binding.root
    }

}