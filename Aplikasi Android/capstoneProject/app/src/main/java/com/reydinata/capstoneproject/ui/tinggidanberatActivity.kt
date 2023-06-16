package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityTinggidanberatBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.ExerciseViewModel

class tinggidanberatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTinggidanberatBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<ExerciseViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinggidanberat)
        binding = ActivityTinggidanberatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBacktinggiberat.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnNext.setOnClickListener{
            var tinggi = binding.etHeight.text
            var berat = binding.etWeight.text
            var username = binding.etUsername.text
            var password = binding.etPassword.text
            if(tinggi.isEmpty() && berat.isEmpty() && username.isEmpty() && password.isEmpty()){
                showAlertDialog("Error", "Mohon isi semua kolom")
            }
            else{
            val intent = Intent(this, AktivitasOlahragaActivity::class.java)
            startActivity(intent)
        }
        }
        val color = ContextCompat.getColor(this, R.color.teal_200)
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                DrawableCompat.setTint(binding.imgBacktinggiberat.drawable,color)

            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onBackPressed() {

    }
}