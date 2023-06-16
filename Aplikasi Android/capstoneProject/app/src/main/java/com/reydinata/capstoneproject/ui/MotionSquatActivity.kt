package com.reydinata.capstoneproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.reydinata.capstoneproject.R

import com.reydinata.capstoneproject.databinding.ActivityMotionSquatBinding

class MotionSquatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMotionSquatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_squat)

        binding = ActivityMotionSquatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.webviewsquat.restoreState(savedInstanceState)
        } else {
            binding.webviewsquat.loadUrl("https://vitality2-389811.et.r.appspot.com/squat-classes")
        }
        val webSettings: WebSettings = binding.webviewsquat.settings
        webSettings.javaScriptEnabled = true
        binding.webviewsquat.webViewClient = WebViewClient()
    }

    }
