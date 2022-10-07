package com.example.categorynoteapp

import android.app.Application
import com.example.categorynoteapp.repository.CategoryNotificationRepository

class CategoryNotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CategoryNotificationRepository.initialize(this)
    }
}