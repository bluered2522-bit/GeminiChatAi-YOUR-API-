package com.aichat.gemini.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.aichat.gemini.R
import com.aichat.gemini.util.Prefs

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = Prefs(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val etApiKey = findViewById<EditText>(R.id.etApiKey)
        val etModel = findViewById<EditText>(R.id.etModel)
        val switchDarkMode = findViewById<Switch>(R.id.switchDarkMode)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        etApiKey.setText(prefs.geminiApiKey)
        etModel.setText(prefs.geminiModel)
        switchDarkMode.isChecked = prefs.darkMode

        btnSave.setOnClickListener {
            prefs.geminiApiKey = etApiKey.text.toString().trim()
            prefs.geminiModel = etModel.text.toString().trim().ifEmpty { Prefs.DEFAULT_MODEL }
            val darkModeChanged = prefs.darkMode != switchDarkMode.isChecked
            prefs.darkMode = switchDarkMode.isChecked

            AppCompatDelegate.setDefaultNightMode(
                if (prefs.darkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )

            Toast.makeText(this, "Pengaturan disimpan", Toast.LENGTH_SHORT).show()
            if (!darkModeChanged) finish()
        }

        btnLogout.setOnClickListener {
            prefs.clearSession()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
