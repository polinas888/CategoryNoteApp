package com.example.categorynoteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.categorynoteapp.notesenders.*
import com.example.categorynoteapp.ui.category.CategoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupFirstFragmentIfNotSetup(savedInstanceState, supportFragmentManager)

        val remoteControlSender = RemoteControlSender()
        val noteEmailSender = NoteEmailSender()
        val noteLetterSender = NoteLetterSender()
        val notePigeonSender = NotePigeonSender()

        val sendEmailCommand = SendEmailCommand(noteEmailSender)
        val sendLetterCommand = SendLetterCommand(noteLetterSender)
        val sendByPigeonCommand = SendByPigeonCommand(notePigeonSender)

        val returnEmailCommand = ReturnEmailCommand(noteEmailSender)
        val returnLetterCommand = ReturnLetterCommand(noteLetterSender)
        val returnPigeonCommand = ReturnPigeonCommand(notePigeonSender)

        remoteControlSender.setCommand(0, sendEmailCommand, returnEmailCommand)
        remoteControlSender.setCommand(1, sendLetterCommand, returnLetterCommand)
        remoteControlSender.setCommand(2, sendByPigeonCommand, returnPigeonCommand)

        remoteControlSender.sendButtonWasPressed(0)
        remoteControlSender.returnButtonWasPressed(0)
        remoteControlSender.sendButtonWasPressed(1)
        remoteControlSender.returnButtonWasPressed(1)
    }

    // Single Responsibility Principle, created method setupFirstFragmentIfNotSetup
    private fun setupFirstFragmentIfNotSetup(savedInstanceState: Bundle?, supportFragmentManager: FragmentManager) {
        if (savedInstanceState == null) {
            addFragment(CategoryFragment(), R.id.container)
        }
    }

    some bug... build shoud failed
}