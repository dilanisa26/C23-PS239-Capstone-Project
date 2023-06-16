package com.reydinata.capstoneproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityMotionDeathliftBinding


class MotionDeathLiftActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMotionDeathliftBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_deathlift)
        binding = ActivityMotionDeathliftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.webviewdeathlift.restoreState(savedInstanceState)
        } else {
            binding.webviewdeathlift.loadUrl("https://vitality2-389811.et.r.appspot.com/pu-classes")
        }
        val webSettings: WebSettings = binding.webviewdeathlift.settings
        webSettings.javaScriptEnabled = true
        binding.webviewdeathlift.webViewClient = WebViewClient()
    }
}