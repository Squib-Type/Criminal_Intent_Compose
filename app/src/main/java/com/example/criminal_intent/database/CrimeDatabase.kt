package com.example.criminal_intent.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.criminal_intent.Crime
import androidx.room.Database as Database1


@Database1(entities = [Crime:: class], version=1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase: RoomDatabase() {
    abstract fun crimeDAO(): CrimeDao


}