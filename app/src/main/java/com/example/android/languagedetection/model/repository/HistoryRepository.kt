package com.example.android.languagedetection.model.repository

import com.example.android.languagedetection.model.database.History
import com.example.android.languagedetection.model.database.HistoryDb
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HistoryRepository @Inject constructor(
        private val historyDb: HistoryDb
) {


    fun loadHistory() = historyDb.historyDao.all
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}