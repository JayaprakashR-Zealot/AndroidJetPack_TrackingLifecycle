package com.truedreamz.lifecycletracking.addItem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.truedreamz.lifecycletracking.db.AppRoomDatabase;
import com.truedreamz.lifecycletracking.db.StudentModel;


public class AddStudentViewModel extends AndroidViewModel {

    private AppRoomDatabase appDatabase;

    public AddStudentViewModel(Application application) {
        super(application);

        appDatabase = AppRoomDatabase.getDatabase(this.getApplication());

    }

    public void addStudent(final StudentModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<StudentModel, Void, Void> {

        private AppRoomDatabase db;

        addAsyncTask(AppRoomDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final StudentModel... params) {
            db.itemAndStudentModel().addStudent(params[0]);
            return null;
        }

    }
}
