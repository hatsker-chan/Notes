package com.example.notes.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.example.notes.R
import com.example.notes.databinding.ActivitySettingsBinding
import com.example.notes.presentation.NoteListActivity.Companion.EXTRA_THEME_KEY



class SettingsActivity : AppCompatActivity() {

    private val settings by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    private val binding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(settings.getInt(EXTRA_THEME_KEY, -1))
        setContentView(binding.root)

        binding.rgThemes.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.rbGreenTheme.id -> {
                    group.check(checkedId)
                }

                binding.rbGreenTheme.id -> {
                    group.check(checkedId)
                }

                binding.rbGreenTheme.id -> {
                    group.check(checkedId)
                }
            }
        }

        binding.btSaveSettings.setOnClickListener {
            when (binding.rgThemes.checkedRadioButtonId){
                binding.rbGreenTheme.id -> settings.edit().putInt(EXTRA_THEME_KEY, R.style.Green_Theme_Notes).apply()
                binding.rbPurpleTheme.id -> settings.edit().putInt(EXTRA_THEME_KEY, R.style.Purple_Theme_Notes).apply()
                binding.rbRedTheme.id -> settings.edit().putInt(EXTRA_THEME_KEY, R.style.Red_Theme_Notes).apply()
                binding.rbBlueTheme.id -> settings.edit().putInt(EXTRA_THEME_KEY, R.style.Blue_Theme_Notes).apply()
            }

            application.setTheme(settings.getInt(EXTRA_THEME_KEY, -1))
            finish()
            startActivity(NoteListActivity.newIntent(this))
        }
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}