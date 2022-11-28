package com.example.categorynoteapp.notesenders


class ReturnEmailCommand(private val noteEmailSender: NoteEmailSender): Command {
    override fun execute() {
        noteEmailSender.returnNote()
    }
}


class ReturnLetterCommand(private val noteLetterSender: NoteLetterSender):Command {
    override fun execute() {
        noteLetterSender.returnNote()
    }
}


class ReturnPigeonCommand(private val notePigeonSender: NotePigeonSender):Command {
    override fun execute() {
        notePigeonSender.returnNote()
    }
}