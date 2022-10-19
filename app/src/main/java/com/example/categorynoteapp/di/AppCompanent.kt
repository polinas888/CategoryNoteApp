package com.example.categorynoteapp.di

import android.content.Context
import com.example.categorynoteapp.ui.category.CategoryFragment
import com.example.categorynoteapp.ui.note.NoteFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule:: class, ViewModelFactoryModule::class, RepositoryFactoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(fragment: CategoryFragment)
    fun inject(fragment: NoteFragment)
}