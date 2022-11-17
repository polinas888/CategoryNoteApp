package com.example.categorynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.categorynoteapp.repository.CategoryNoteDatabase
import com.example.categorynoteapp.ui.category.CategoryFragment

private const val DATABASE_NAME = "category-note-database"
class MainActivity : AppCompatActivity() {

    init {
        context = this
    }

    // First version of database singleton
//    companion object {
//        private var context: MainActivity? = null
//        private var database: CategoryNoteDatabase? = null
//
//        @Synchronized fun getDatabase(): CategoryNoteDatabase? {
//            if(database == null) {
//                database = context?.applicationContext?.let {
//                    Room.databaseBuilder(
//                        it,
//                        CategoryNoteDatabase::class.java,
//                        DATABASE_NAME
//                    ).build()
//                }
//            }
//            return database
//        }
//    }

    // Second version of database singleton
    companion object {
        private var context: MainActivity? = null

        @Volatile
        private var database: CategoryNoteDatabase? = null

        fun getDatabase(): CategoryNoteDatabase? {
            if (database == null) {
                @Synchronized
                if (database == null) {
                    database = context?.applicationContext?.let {
                        Room.databaseBuilder(
                            it,
                            CategoryNoteDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return database
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupFirstFragmentIfNotSetup(savedInstanceState, supportFragmentManager)
    }

    // Single Responsibility Principle, created method setupFirstFragmentIfNotSetup
    private fun setupFirstFragmentIfNotSetup(savedInstanceState: Bundle?, supportFragmentManager: FragmentManager) {
        if (savedInstanceState == null) {
            addFragment(CategoryFragment(), R.id.container)
        }
    }
}