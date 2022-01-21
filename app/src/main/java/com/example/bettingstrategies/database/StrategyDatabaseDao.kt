/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bettingstrategies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface StrategyDatabaseDao {

    @Insert
    suspend fun insert(strategy: StrategyData)

    @Query("DELETE FROM favorite_strategies_table WHERE title = :key")
    suspend fun deleteByTitle(key: String)

//    @Query("SELECT * from favorite_strategies_table WHERE title = :key")
//    suspend fun getByTitle(key: String): StrategyData

    @Query("SELECT * FROM favorite_strategies_table ORDER BY title DESC")
    fun getStrategies(): LiveData<List<StrategyData>>

    @Query("SELECT title FROM favorite_strategies_table")
    fun getStrategiesTitle(): LiveData<List<String>>

    @Query("SELECT * from favorite_strategies_table WHERE title = :key")
    fun getByTitle(key: String): LiveData<StrategyData>
}
