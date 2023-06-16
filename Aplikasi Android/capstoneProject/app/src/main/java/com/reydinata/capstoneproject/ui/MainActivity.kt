package com.reydinata.capstoneproject.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.reydinata.capstoneproject.R
import com.reydinata.capstoneproject.databinding.ActivityMainBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.MainViewModel
import java.lang.Float
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<MainViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "home"
        val sharedPreferences = getSharedPreferences("MyUsername", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        binding.txtUsername.setText("Hello " + username)
        binding.btnActivity.setOnClickListener{
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.chatbotButton.setOnClickListener{
            openChatbotActivity()
        }
        binding.chatbotButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.livechat));
        binding.calculateButton.setOnClickListener{
            calculateBmi()
        }
        binding.btndetail.setOnClickListener{
            showAlertDialog("Detail Explanation", "Menurut WHO, kategori standar berat badan ideal pria dan wanita dewasa berdasarkan BMI adalah sebagai berikut:\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Kurang dari 18,5 berarti berat badan kurang (underweight).\n" +
                    "Antara 18,5 - 24,9 berarti berat badan normal\n" +
                    "Antara 25-29,9 berarti berat badan berlebih (overweight).\n" +
                    "Di atas 30 berarti obesitas")
        }
        val color = ContextCompat.getColor(this, R.color.teal_200)
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                DrawableCompat.setTint(binding.btndetail.drawable,color)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


    }
    private fun calculateBmi(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input Berat dan Tinggi")


        val weightBmi = binding.weightEditText.text.toString()
        val heightBmi = binding.heightEditText.text.toString()


        if (weightBmi.isNotEmpty() && heightBmi.isNotEmpty()) {
            val weight = parseFloat(weightBmi)
            val height = parseFloat(heightBmi) / 100
            val bmi = weight / (height * height)
            if (weight > 0  && height > 1 ) {

                binding.resultTextView.text = "BMI: $bmi"
            } else {
                showAlertDialog("Error","Masukkan angka yang valid")
            }
        } else {
            showAlertDialog("Error","Isikan kolom")
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
    private fun openChatbotActivity() {
        val intent = Intent(this, ChatbotActivity::class.java)
        startActivity(intent)
    }    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {

                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {

    }
}