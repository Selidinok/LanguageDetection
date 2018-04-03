package com.example.android.languagedetection.model.interceptor

import com.example.android.languagedetection.model.repository.NewTextRepository
import javax.inject.Inject

class NewTextInteractor @Inject constructor(
        private val newTextRepository: NewTextRepository
){
    fun getLanguage(text: String) = newTextRepository.getLanguage(text)

    fun inset(text: String, language: String) = newTextRepository.insert(text, language)
}