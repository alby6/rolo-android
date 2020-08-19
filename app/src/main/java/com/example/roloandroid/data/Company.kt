package com.example.roloandroid.data

import androidx.room.ColumnInfo
import androidx.room.Entity


data class Company(
    @ColumnInfo(name = "company_name") val companyName : String,
    @ColumnInfo(name = "catch_phrase") val catchPhrase : String
)