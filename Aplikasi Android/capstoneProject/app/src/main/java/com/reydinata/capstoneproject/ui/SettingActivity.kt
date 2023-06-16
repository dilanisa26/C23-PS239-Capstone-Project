package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.material.switchmaterial.SwitchMaterial
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivitySettingBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.SettingViewModel


class SettingActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySettingBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val settingViewModel: SettingViewModel by viewModels{
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lblobjective.setOnClickListener {
            val intent = Intent(this,ObjectiveActivity::class.java)
            startActivity(intent)
        }
        binding.lbleditprofile.setOnClickListener {
            val intent = Intent(this,EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.lbllogout.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.backButtonSetting.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        settingViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
               switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }


    }






}