package com.truedreamz.lifecycletracking.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.TypeConverters

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
@TypeConverters(DateConverter::class)
interface PersonModelDao {

    @get:Query("select * from PersonModel")
    val allItems: LiveData<List<PersonModel>>

    @Query("select * from PersonModel where id = :id")
    fun getItembyId(id: String): PersonModel

    @Insert(onConflict = REPLACE)
    fun addPerson(personModel: PersonModel)

    @Delete
    fun deletePerson(personModel: PersonModel)
}
