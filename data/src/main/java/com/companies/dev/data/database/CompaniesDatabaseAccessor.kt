package com.companies.dev.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.companies.dev.domain.entity.Company

/**
 * @author Ivan Prokopyev
 */
@Dao
interface CompaniesDatabaseAccessor {

    @Query("SELECT * FROM company")
    fun selectAll(): List<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Company>)

    @Query("SELECT * FROM company WHERE name LIKE '%' || :searchedName || '%'")
    fun selectByName(searchedName: String): List<Company>
}