package com.example.categorynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.categorynoteapp.ui.category.CategoryFragment

class MainActivity : AppCompatActivity() {
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
            supportFragmentManager.beginTransaction().add(R.id.container, CategoryFragment()).commit()
        }
    }
}