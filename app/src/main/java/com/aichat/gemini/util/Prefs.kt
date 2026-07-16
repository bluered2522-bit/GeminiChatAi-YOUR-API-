package com.aichat.gemini.util

import android.content.Context

class Prefs(context: Context) {
    private val sp = context.applicationContext.getSharedPreferences("geminichat_prefs", Context.MODE_PRIVATE)

    var loggedInUserId: Long
        get() = sp.getLong(KEY_USER_ID, -1L)
        set(value) = sp.edit().putLong(KEY_USER_ID, value).apply()

    var loggedInUsername: String
        get() = sp.getString(KEY_USERNAME, "") ?: ""
        set(value) = sp.edit().putString(KEY_USERNAME, value).apply()

    var geminiApiKey: String
        get() = sp.getString(KEY_API_KEY, "") ?: ""
        set(value) = sp.edit().putString(KEY_API_KEY, value).apply()

    var geminiModel: String
        get() = sp.getString(KEY_MODEL, DEFAULT_MODEL) ?: DEFAULT_MODEL
        set(value) = sp.edit().putString(KEY_MODEL, value).apply()

    var darkMode: Boolean
        get() = sp.getBoolean(KEY_DARK_MODE, false)
        set(value) = sp.edit().putBoolean(KEY_DARK_MODE, value).apply()

    var lastSessionId: Long
        get() = sp.getLong(KEY_LAST_SESSION, -1L)
        set(value) = sp.edit().putLong(KEY_LAST_SESSION, value).apply()

    fun clearSession() {
        sp.edit().remove(KEY_USER_ID).remove(KEY_USERNAME).remove(KEY_LAST_SESSION).apply()
    }

    companion object {
        private const val KEY_USER_ID = "logged_in_user_id"
        private const val KEY_USERNAME = "logged_in_username"
        private const val KEY_API_KEY = "gemini_api_key"
        private const val KEY_MODEL = "gemini_model"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_LAST_SESSION = "last_session_id"
        const val DEFAULT_MODEL = "gemini-3.5-flash"
    }
}
