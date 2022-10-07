package com.example.categorynoteapp.ui.notification

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.categorynoteapp.model.Notification
import com.example.categorynoteapp.repository.notification.NotificationRepository
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val notificationRepository: NotificationRepository) :
    ViewModel() {
    val notificationListLiveData = MutableLiveData<List<Notification>>()
    val categoryId = MutableLiveData(0)

    suspend fun saveNotification(notification: Notification) {
        try {
            notificationRepository.addNotification(notification)
        } catch (exception: SQLiteConstraintException) {
            Log.i("NotificationLog", "Couldnt save notification")
        }
    }
}