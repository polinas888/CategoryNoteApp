package com.example.categorynoteapp.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String?,
    var text: String?
) {
    data class Builder(
        var id: Int = 0,
        var name: String? = null,
        var text: String? = null) {

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun text(text: String) = apply { this.text = text }
        fun build() = Category(id, name, text)
    }
}