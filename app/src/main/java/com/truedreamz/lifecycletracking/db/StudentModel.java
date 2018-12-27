package com.truedreamz.lifecycletracking.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private String personName;
    @TypeConverters(DateConverterUtil.class)
    private Date addedDate;

    public StudentModel(String itemName, String personName, Date addedDate) {
        this.itemName = itemName;
        this.personName = personName;
        this.addedDate = addedDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getAddedDate() {
        return addedDate;
    }
}
