package com.truedreamz.lifecycletracking.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.truedreamz.lifecycletracking.db.AppRoomDatabase;
import com.truedreamz.lifecycletracking.db.StudentModel;

import java.util.List;


public class StudentListViewModel extends AndroidViewModel {

    private final LiveData<List<StudentModel>> itemAndPersonList;

    private AppRoomDatabase appDatabase;

    public StudentListViewModel(Application application) {
        super(application);
        appDatabase = AppRoomDatabase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndStudentModel().getAllStudItems();
    }


    public LiveData<List<StudentModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(StudentModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        private AppRoomDatabase db;

        deleteAsyncTask(AppRoomDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final StudentModel... params) {
            db.itemAndStudentModel().deleteStudent(params[0]);
            return null;
        }
    }
}
