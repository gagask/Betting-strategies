package com.example.bettingstrategies.strategies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bettingstrategies.MyViewModelFactory
import com.example.bettingstrategies.StrategiesAdapter
import com.example.bettingstrategies.database.StrategyDatabase
import com.example.bettingstrategies.databinding.FragmentStrategiesBinding

class StrategiesFragment : Fragment() {

    private lateinit var binding: FragmentStrategiesBinding
    private lateinit var viewModel: StrategiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStrategiesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = StrategyDatabase.getInstance(application).strategyDatabaseDao
        val viewModelFactory = MyViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[StrategiesViewModel::class.java]
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
                findNavController().navigate(StrategiesFragmentDirections.actionStrategiesFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        viewModel.favoriteStrategies.observe(viewLifecycleOwner){
            viewModel.updateFavoriteStars(it)
        }

        return binding.root
    }
}