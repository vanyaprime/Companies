package com.companies.dev.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.companies.dev.domain.entity.Company

/**
 * @author Ivan Prokopyev
 */
@Database(entities = [Company::class], version = 1)
abstract class CompaniesDatabase : RoomDatabase() {

    abstract fun accessor(): CompaniesDatabaseAccessor
}