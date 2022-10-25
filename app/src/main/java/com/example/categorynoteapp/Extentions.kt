package com.example.categorynoteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

fun Fragment.changeFragment(args: Bundle, fragmentManager: FragmentManager) {
    this.arguments = args

    fragmentManager.commit {
        addToBackStack(null)
        replace(R.id.container, this@changeFragment)
    }
}

/* Single Responsibility Principle method close fragment
 + removed method to Utils from noteCreateOrChangeFragment */
fun Fragment.closeFragment() {
    val manager: FragmentManager = requireActivity().supportFragmentManager
    val trans: FragmentTransaction = manager.beginTransaction()
    trans.remove(this)
    trans.commit()
    manager.popBackStack()
}