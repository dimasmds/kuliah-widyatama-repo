package com.widyatama.moviedirectory.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.fragment.SettingFragment
import com.widyatama.moviedirectory.utils.ThemeUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.themeState(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        fragmentManager.beginTransaction()
                .replace(R.id.settingContentLayout, SettingFragment())
                .commit()

        setSupportActionBar(settingToolbar)
        supportActionBar?.title = getString(R.string.setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        ThemeUtils.themeState(this)
    }
}
