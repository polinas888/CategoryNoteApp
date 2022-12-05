package com.example.categorynoteapp

import android.app.Application
import android.content.Context
import com.example.categorynoteapp.di.AppComponent
import com.example.categorynoteapp.di.DaggerAppComponent
import com.example.categorynoteapp.repository.DatabaseFactory

class CategorynoteApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        DatabaseFactory.initialize(this)
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is CategorynoteApplication -> appComponent
        else -> this.applicationContext.appComponent
    }