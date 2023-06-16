package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityExerciseBinding
import com.reydinata.capstoneproject.databinding.ActivityObjectiveBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.MainViewModel

class ObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObjectiveBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<MainViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objective)
        binding = ActivityObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButtonEditObj.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val color = ContextCompat.getColor(this, R.color.teal_200)
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                DrawableCompat.setTint(binding.backButtonEditObj.drawable,color)

            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}