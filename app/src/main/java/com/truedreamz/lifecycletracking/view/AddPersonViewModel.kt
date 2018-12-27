package com.truedreamz.lifecycletracking.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.AsyncTask

import com.truedreamz.lifecycletracking.db.AppDatabase
import com.truedreamz.lifecycletracking.db.PersonModel


class AddPersonViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase: AppDatabase

    init {
        appDatabase = AppDatabase.getDatabase(this.getApplication())
    }

    fun addPerson(personModel: PersonModel) {
        addAsyncTask(appDatabase).execute(personModel)
    }

    private class addAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<PersonModel, Void, Void>() {

        override fun doInBackground(vararg params: PersonModel): Void? {
            db.itemAndPersonModel().addPerson(params[0])
            return null
        }

    }
}
