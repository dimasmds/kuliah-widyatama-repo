package com.widyatama.moviedirectory.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.provider.Settings
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.reminder.DailyAlarmReceiver
import com.widyatama.moviedirectory.reminder.ReleaseAlarmReceiver


class SettingFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    private val dailyAlarmReceiver = DailyAlarmReceiver()
    private val releaseAlarmReceiver = ReleaseAlarmReceiver()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        addPreferencesFromResource(R.xml.setting_preference)

        val dailyReminder = getString(R.string.pref_daily_reminder)
        val realeaseReminder = getString(R.string.pref_release_reminder)
        val localizationPreference = getString(R.string.pref_language)
        val theme = getString(R.string.pref_theme)


        findPreference(dailyReminder).onPreferenceChangeListener = this
        findPreference(realeaseReminder).onPreferenceChangeListener = this
        findPreference(theme).onPreferenceChangeListener = this
        findPreference(localizationPreference).onPreferenceClickListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        val key = preference.key
        val isOn = newValue as Boolean

        if (key == getString(R.string.pref_daily_reminder)) {
            if (isOn) {
                dailyAlarmReceiver.setAlarm(activity,
                        getString(R.string.daily_content_text))
            } else {
                dailyAlarmReceiver.cancelAlarm(activity)
            }
        } else if (key == getString(R.string.pref_release_reminder)) {
            if (isOn) {
                releaseAlarmReceiver.setAlarm(activity,
                        getString(R.string.notif_new_movie))
            } else {
                releaseAlarmReceiver.cancelAlarm(activity)
            }
        } else {
            activity.recreate()
        }

        return true

    }

    override fun onPreferenceClick(preference: Preference): Boolean {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
        return true
    }
}// Required empty public constructor