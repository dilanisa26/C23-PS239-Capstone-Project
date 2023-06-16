package com.reydinata.capstoneproject.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityChatbotBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.ExerciseViewModel


class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatbotBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<ExerciseViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbarchatbot
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Chatbot"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            onBackPressed1()
        }

        if (savedInstanceState != null) {
            binding.webviewchatbot.restoreState(savedInstanceState)
        } else {
            binding.webviewchatbot.loadUrl("https://vitality123.et.r.appspot.com/")
        }
        val webSettings: WebSettings = binding.webviewchatbot.settings
        webSettings.javaScriptEnabled = true
        binding.webviewchatbot.webViewClient = WebViewClient()
        val color = ContextCompat.getColor(this, R.color.teal_200)
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed1()
        return true
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webviewchatbot.saveState(outState)
    }
    fun onBackPressed1() {

        val bundle = Bundle()
        binding.webviewchatbot.saveState(bundle)


        super.onBackPressed()



    }

}


