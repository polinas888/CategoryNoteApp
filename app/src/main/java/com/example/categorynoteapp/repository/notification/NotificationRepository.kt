package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Notification
import javax.inject.Inject

class NotificationRepository @Inject constructor(notificationDao: NotificationDao): NotificationDao {

    private val notificationDao: NotificationDao by lazy {
        notificationDao
    }

    override suspend fun addNotification(notification: Notification) {
        notificationDao.addNotification(notification)
    }

}