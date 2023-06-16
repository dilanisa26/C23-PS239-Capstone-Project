package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityLoginBinding

import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<MainViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.etPasswordLogin.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.length < 8) {
                        val errorText = "<font color='#FF0000'>Password must be at least 8 characters long</font>"
                       binding.etPasswordLogin.setError(Html.fromHtml(errorText))
                    } else {
                        binding.etPasswordLogin.setError(null)
                    }
                }
            })
        binding.btnLogin.setOnClickListener{
            val user = binding.etUsernameLogin.text.toString()
            val sharedPreferences = getSharedPreferences("MyUsername", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", user)
            editor.apply()
            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)

        }
        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this,tinggidanberatActivity::class.java)
            startActivity(intent)
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


}