package com.example.categorynoteapp.repository.notification

import androidx.room.*
import com.example.categorynoteapp.model.Notification

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notification WHERE category_id = :categoryId")
    suspend fun getNotifications(categoryId: Int): List<Notification>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotification(notification: Notification)

    @Delete
    suspend fun deleteNotification(notification: Notification)
}