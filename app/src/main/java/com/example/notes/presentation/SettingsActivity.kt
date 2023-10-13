package com.example.notes.presentation

import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        setTheme(settings.getInt(EXTRA_THEME_KEY, R.style.Green_Theme_Notes))
        binding.rgThemes.check(settings.getInt(KEY_CHECKED_RADIOBUTTON, R.id.rb_green_theme))
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.setting_screen_action_bar)

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
            when (binding.rgThemes.checkedRadioButtonId) {
                binding.rbGreenTheme.id -> settings.edit().apply {
                    putInt(EXTRA_THEME_KEY, R.style.Green_Theme_Notes).apply()
                    putInt(KEY_CHECKED_RADIOBUTTON, binding.rbGreenTheme.id).apply()
                }


                binding.rbPurpleTheme.id -> settings.edit().apply {
                    putInt(EXTRA_THEME_KEY, R.style.Purple_Theme_Notes).apply()
                    putInt(KEY_CHECKED_RADIOBUTTON, binding.rbPurpleTheme.id).apply()
                }

                binding.rbRedTheme.id -> settings.edit().apply {
                    putInt(EXTRA_THEME_KEY, R.style.Red_Theme_Notes).apply()
                    putInt(KEY_CHECKED_RADIOBUTTON, binding.rbRedTheme.id).apply()
                }

                binding.rbBlueTheme.id -> settings.edit().apply {
                    putInt(EXTRA_THEME_KEY, R.style.Blue_Theme_Notes).apply()
                    putInt(KEY_CHECKED_RADIOBUTTON, binding.rbBlueTheme.id).apply()
                }
            }

            application.setTheme(settings.getInt(EXTRA_THEME_KEY, -1))
            TaskStackBuilder.create(this)
                .addNextIntent(NoteListActivity.newIntent(this))
                .addNextIntent(this.intent)
                .startActivities()
        }
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }

        private const val KEY_CHECKED_RADIOBUTTON = "radioButton"
    }
}