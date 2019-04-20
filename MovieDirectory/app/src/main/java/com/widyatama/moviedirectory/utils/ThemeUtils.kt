package com.widyatama.moviedirectory.utils

import android.content.Context
import android.preference.PreferenceManager
import com.widyatama.moviedirectory.R

class ThemeUtils {

    companion object {
        fun themeState(context: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val isDark = preferences.getBoolean(context.resources.getString(R.string.pref_theme), false)
            if (isDark) {
                context.setTheme(R.style.AppTheme_Dark)
            } else {
                context.setTheme(R.style.AppTheme_Light)
            }
        }

        fun themeDetailState(context: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val isDark = preferences.getBoolean(context.resources.getString(R.string.pref_theme), false)
            if (isDark) {
                context.setTheme(R.style.AppTheme_Dark_Detail)
            } else {
                context.setTheme(R.style.AppTheme_Light_Detail)
            }
        }
    }
}
