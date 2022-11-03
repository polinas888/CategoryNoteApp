package com.example.categorynoteapp

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.categorynoteapp.ui.category.CategoryFragment

fun Fragment.changeFragment(args: Bundle, fragmentManager: FragmentManager) {
    this.arguments = args

    fragmentManager.commit {
        addToBackStack(null)
        replace(R.id.container, this@changeFragment)
    }
}

fun AppCompatActivity.addFragment(
    newFragment: Fragment,
    containerID : Int = R.id.container) {
    supportFragmentManager.beginTransaction().add(containerID, newFragment).commit()
}

/* Single Responsibility Principle method close fragment
 + removed method to Utils from noteCreateOrChangeFragment */
fun Fragment.closeFragment() {
    val manager: FragmentManager = this.parentFragmentManager
    val trans: FragmentTransaction = manager.beginTransaction()
    trans.remove(this)
    trans.commit()
    manager.popBackStack()
}