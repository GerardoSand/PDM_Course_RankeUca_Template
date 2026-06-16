package com.pdmcourse2026.basictemplate.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmcourse2026.basictemplate.data.entities.OptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {

    @Query("SELECT * FROM options WHERE questionId = :questionId")
    fun getOptionsForQuestion(questionId: Int): Flow<List<OptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: OptionEntity)

    @Delete
    suspend fun deleteOption(option: OptionEntity)

    @Query("UPDATE options SET votes = votes + 1 WHERE id = :optionId")
    suspend fun vote(optionId: Int)
}
