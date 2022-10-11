package com.example.categorynoteapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/* Single Responsibility Principle method close fragment
 + removed method to Utils from noteCreateOrChangeFragment */
fun closeFragment(fragment: Fragment) {
    val manager: FragmentManager = fragment.requireActivity().supportFragmentManager
    val trans: FragmentTransaction = manager.beginTransaction()
    trans.remove(fragment)
    trans.commit()
    manager.popBackStack()
}