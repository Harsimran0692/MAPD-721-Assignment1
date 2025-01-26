package com.example.mapd_721_assignment1

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_data")

class DataStore(private val context: Context) {

    private val STUDENT_ID_KEY = stringPreferencesKey("student_id")
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val COURSE_NAME_KEY = stringPreferencesKey("course_name")

    // Save data to DataStore
    suspend fun saveData(studentId: String, userName: String, courseName: String) {
        context.dataStore.edit { preferences ->
            preferences[STUDENT_ID_KEY] = studentId
            preferences[USER_NAME_KEY] = userName
            preferences[COURSE_NAME_KEY] = courseName
        }
    }

    // Retrieve data from DataStore
    fun getData() = context.dataStore.data.map { preferences ->
        Triple(
            preferences[STUDENT_ID_KEY] ?: "",
            preferences[USER_NAME_KEY] ?: "",
            preferences[COURSE_NAME_KEY] ?: ""
        )
    }

    suspend fun clearData() {
        context.dataStore.edit { it.clear() }
    }
}
