package com.example.bettingstrategies.favorite

import android.app.Application
import androidx.lifecycle.*
import com.example.bettingstrategies.database.Strategy
import com.example.bettingstrategies.database.StrategyData
import com.example.bettingstrategies.database.StrategyDatabaseDao
import kotlinx.coroutines.launch

class FavoriteViewModel(dataSource: StrategyDatabaseDao, application: Application) : ViewModel() {

    val database = dataSource

    private val _navigateToSelectedStrategy = MutableLiveData<Strategy>()
    val navigateToSelectedStrategy: LiveData<Strategy>
        get() = _navigateToSelectedStrategy

    fun displayDetails(strategy: Strategy) {
        _navigateToSelectedStrategy.value = strategy
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedStrategy.value = null
    }

    private val favoriteStrategies = database.getStrategies()

    val strategies = Transformations.map(favoriteStrategies) { favorites ->
        favorites.map {
            Strategy(it, true)
        }
    }

    fun updateFavoritesDB(strategy: Strategy) {
        viewModelScope.launch {
            if(strategy.isFavorite == true){
                database.deleteByTitle(strategy.title)
            }
            else {
                database.insert(StrategyData(strategy.title, strategy.imgSrcUrl, strategy.description))
            }
        }
    }
}