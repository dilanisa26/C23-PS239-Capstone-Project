package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityAktivitasOlahragaBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.ExerciseViewModel


class AktivitasOlahragaActivity : AppCompatActivity() {
private lateinit var binding: ActivityAktivitasOlahragaBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<ExerciseViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aktivitas_olahraga)
        binding = ActivityAktivitasOlahragaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBackaktivitas.setOnClickListener{
            val intent = Intent(this,tinggidanberatActivity::class.java)
            startActivity(intent)
        }
        binding.btnNext.setOnClickListener{
            var sering = binding.radioButtonsering
            var jarang = binding.radioButtonjarang
            var tidakPernah = binding.radioButtontidakpernah
            if(sering.isChecked || jarang.isChecked || tidakPernah.isChecked){
                val intent = Intent(this, RegistrasiSasaranActivity::class.java)
                startActivity(intent)
            }
            else{
                showAlertDialog("Error" , "Pilih salah satu")
            }

        }
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
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