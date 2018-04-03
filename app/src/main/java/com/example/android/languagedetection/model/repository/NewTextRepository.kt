package com.example.android.languagedetection.model.repository

import com.example.android.languagedetection.model.database.History
import com.example.android.languagedetection.model.database.HistoryDb
import com.example.android.languagedetection.model.network.LanguageApi
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewTextRepository @Inject constructor(
        private val api: LanguageApi,
        private val historyDb: HistoryDb
        ) {
    companion object {
        private const val API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78"
    }

    fun getLanguage(text: String) = api.getData(API_KEY, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insert(text: String, language: String){
        Completable.fromAction({ historyDb.historyDao.add(History(text, language)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}