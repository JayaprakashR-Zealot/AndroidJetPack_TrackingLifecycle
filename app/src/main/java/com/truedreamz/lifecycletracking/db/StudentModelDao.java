package com.truedreamz.lifecycletracking.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverterUtil.class)
public interface StudentModelDao {

    @Query("select * from StudentModel")
    LiveData<List<StudentModel>> getAllStudItems();

    @Query("select * from StudentModel where id = :id")
    StudentModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addStudent(StudentModel borrowModel);

    @Delete
    void deleteStudent(StudentModel borrowModel);

}
