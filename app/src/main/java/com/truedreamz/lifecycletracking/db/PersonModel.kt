package com.truedreamz.lifecycletracking.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

import java.util.Date

@Entity
class PersonModel(val itemName: String, val personName: String, @field:TypeConverters(DateConverter::class)
val addedDate: Date) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
