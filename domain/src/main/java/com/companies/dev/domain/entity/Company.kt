package com.companies.dev.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Ivan Prokopyev
 */
@Entity
data class Company(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val imgUrl: String
)