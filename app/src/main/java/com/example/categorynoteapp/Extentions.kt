package com.example.categorynoteapp

import android.content.Context
import com.example.categorynoteapp.di.AppComponent

val Context.appComponent: AppComponent
    get() = when(this) {
        is CategoryNotificationApplication -> appComponent
        else -> this.applicationContext.appComponent
    }