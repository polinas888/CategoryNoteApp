package com.example.categorynoteapp.notesenders

class RemoteControlSender {

    private var sendCommands = mutableListOf<Command>()
    private var returnCommands = mutableListOf<Command>()

    fun setCommand(slot:Int, sendCommand: Command, returnCommand: Command) {
        sendCommands.add(slot, sendCommand)
        returnCommands.add(slot, returnCommand)
    }

    fun sendButtonWasPressed(slot:Int) {
        sendCommands[slot].execute()
    }

    fun returnButtonWasPressed(slot:Int) {
        returnCommands[slot].execute()
    }
}