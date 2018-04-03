package com.example.android.languagedetection.model.interceptor

import com.example.android.languagedetection.model.repository.HistoryRepository
import com.example.android.languagedetection.model.repository.NewTextRepository
import javax.inject.Inject

class HistoryInteraptor @Inject constructor(
        private val historyRepository: HistoryRepository
) {


    fun getHistory() = historyRepository.loadHistory()
}