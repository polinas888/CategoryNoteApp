package com.example.categorynoteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.categorynoteapp.ui.category.CategoryFragment

fun setupFirstFragmentIfNotSetup(savedInstanceState: Bundle?, supportFragmentManager: FragmentManager) {
    if (savedInstanceState == null) {
        setupFirstFragment(supportFragmentManager)
    }
}

fun setupFirstFragment(supportFragmentManager: FragmentManager) {
    supportFragmentManager.beginTransaction().add(R.id.container, CategoryFragment()).commit()
}

fun Fragment.changeFragment(args: Bundle, fragmentManager: FragmentManager) {
    this.arguments = args

    fragmentManager.commit {
        addToBackStack(null)
        replace(R.id.container, this@changeFragment)
    }
}