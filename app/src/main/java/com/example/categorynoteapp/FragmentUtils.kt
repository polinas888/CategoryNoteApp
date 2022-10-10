package com.example.categorynoteapp

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.categorynoteapp.ui.category.CategoryFragment

fun setupFirstFragmentIfNotSetup(savedInstanceState: Bundle?, supportFragmentManager: FragmentManager) {
    if (savedInstanceState == null) {
        setupFirstFragment(supportFragmentManager)
    }
}

fun setupFirstFragment(supportFragmentManager: FragmentManager) {
    supportFragmentManager.beginTransaction().add(R.id.container, CategoryFragment()).commit()
}