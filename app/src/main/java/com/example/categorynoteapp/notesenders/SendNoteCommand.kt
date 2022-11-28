package com.example.categorynoteapp.notesenders


class SendEmailCommand(private val noteEmailSender: NoteEmailSender): Command {
    override fun execute() {
        noteEmailSender.sendNote()
    }
}


class SendLetterCommand(private val noteLetterSender: NoteLetterSender):Command {
    override fun execute() {
        noteLetterSender.writeLetter()
        noteLetterSender.sendNote()
    }
}


class SendByPigeonCommand(private val notePigeonSender: NotePigeonSender):Command {
    override fun execute() {
        notePigeonSender.writeLetter()
        notePigeonSender.tieLetterToPaw()
        notePigeonSender.releaseBird()
    }
}