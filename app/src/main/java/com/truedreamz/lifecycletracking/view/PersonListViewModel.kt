package com.truedreamz.lifecycletracking.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

import com.truedreamz.lifecycletracking.db.AppDatabase
import com.truedreamz.lifecycletracking.db.PersonModel


class PersonListViewModel(application: Application) : AndroidViewModel(application) {

    val itemAndPersonList: LiveData<List<PersonModel>>

    private val appDatabase: AppDatabase

    init {
        appDatabase = AppDatabase.getDatabase(this.getApplication())
        itemAndPersonList = appDatabase.itemAndPersonModel().allItems
    }

    fun deleteItem(personModel: PersonModel) {
        deleteAsyncTask(appDatabase).execute(personModel)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<PersonModel, Void, Void>() {

        override fun doInBackground(vararg params: PersonModel): Void? {
            db.itemAndPersonModel().deletePerson(params[0])
            return null
        }

    }

}
