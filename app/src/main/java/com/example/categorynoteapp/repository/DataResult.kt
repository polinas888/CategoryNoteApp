package com.example.categorynoteapp.repository

sealed class DataResult<out R> {
    data class Ok<out T>(val response: T) : DataResult<T>()
    data class Error(val error: String?) : DataResult<Nothing>()
}