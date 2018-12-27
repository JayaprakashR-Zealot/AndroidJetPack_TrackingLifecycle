package com.truedreamz.lifecycletracking.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {StudentModel.class}, version = 2)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "student_db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract StudentModelDao itemAndStudentModel();
}
