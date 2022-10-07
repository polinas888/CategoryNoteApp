package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Notification
import javax.inject.Inject

class NotificationRepository @Inject constructor(notificationDao: NotificationDao): NotificationDao {

    override suspend fun getNotifications(): List<Notification> {
        TODO("Not yet implemented")
    }

    override suspend fun addNotification(notification: Notification) {
        TODO("Not yet implemented")
    }

}