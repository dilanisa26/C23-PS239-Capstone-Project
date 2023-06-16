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
import com.reydinata.capstoneproject.databinding.ActivityExerciseBinding
import com.reydinata.capstoneproject.ui.DataStore.SettingPreferences
import com.reydinata.capstoneproject.ui.helper.ViewModelFactory
import com.reydinata.capstoneproject.ui.viewmodel.ExerciseViewModel


class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mainViewModel by viewModels<ExerciseViewModel>(){
        val pref = SettingPreferences.getInstance(dataStore)
        ViewModelFactory.getInstance(application , pref)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar4
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Exercise"
        binding.btnSquat.setOnClickListener{
    val intent = Intent(this, MotionSquatActivity::class.java)
    startActivity(intent)}
binding.btnDeadLift.setOnClickListener{
            val intent = Intent(this, MotionDeathLiftActivity::class.java)
            startActivity(intent)
        }
        binding.btnDetailDeadLift.setOnClickListener{
            showAlertDialog("Cara melakukan DeadLift","berikut langkah-langkah melakukan deadlift:\n" +
                    "\n" +
                    "1. Ambil posisi tegak dengan kaki dibuka selebar bahu dan letakkan barbel di depan kaki. Pegang barbel dan atur napas sebelum mulai mengangkat.\n" +
                    "2. Posisikan kepala untuk membuat tulang punggung lurus sebelum mengangkat. Pertahankan telapak tangan tetap berada di bagian bawah untuk memegang barbel.\n" +
                    "3. Ketika mengangkat beban, tekan kaki untuk tetap rata dengan lantai dan turunkan pinggul ke bagian belakang.\n" +
                    "4. Angkat beban dengan tetap mendekat ke kaki Anda.\n" +
                    "5. Dorong pinggul ke depan hingga Anda berdiri tegak. Pastikan kaki Anda lurus, bahu mengangkat ke belakang, dan lutut terkunci.\n" +
                    "6. Beban harus dipegang dengan lengan lurus dan lebih rendah dibandingkan pinggul.\n" +
                    "7. Kembali ke posisi awal sambil menjaga punggung tetap lurus, pinggul di bagian belakang, lutut ditekuk, dan jongkok hingga beban menyentuh lantai\n" +
                    "8. Ulangi gerakan dari awal")
        }
        binding.btnDetailSquat.setOnClickListener{
            showAlertDialog("Cara melakukan squat","1. Mulailah dengan posisi berdiri tegak dengan kedua kaki selebar bahu. Pastikan tulang belakang dalam posisi lurus dan pandangan mata menghadap ke depan.\n" +
                    "\n" +
                    "2. Kedua tangan dapat ditempatkan di depan dada atau ditekuk dengan siku menghadap ke bawah, sesuai dengan kenyamanan Anda.\n" +
                    "\n" +
                    "3. Mulailah menurunkan tubuh dengan cara menggeser pinggul ke belakang, seolah-olah Anda akan duduk di kursi. Pastikan lutut tetap sejajar dengan arah jari kaki, dan jangan biarkan lutut melewati ujung jari kaki.\n" +
                    "\n" +
                    "4. Lanjutkan menurunkan tubuh hingga paha sejajar dengan lantai. Usahakan untuk membentuk sudut 90 derajat di lutut Anda. Pada tahap ini, berat tubuh sebagian besar didukung oleh tumit dan telapak kaki.\n" +
                    "\n" +
                    "5. Pada saat Anda mencapai posisi terendah, hentikan penurunan tubuh dan pastikan keseimbangan Anda terjaga dengan baik. Punggung tetap lurus, dan pandangan mata tetap di depan.\n" +
                    "\n" +
                    "6. Setelah itu, dorong tubuh Anda dengan menggunakan tumit dan kembali ke posisi berdiri tegak. Pastikan untuk mengendalikan gerakan dan tidak melompat saat naik kembali.\n" +
                    "\n" +
                    "7. Ulangi langkah-langkah tersebut untuk beberapa kali sesuai dengan kemampuan dan kebutuhan Anda. Disarankan untuk memulai dengan jumlah repetisi yang lebih rendah dan secara bertahap meningkatkannya seiring waktu.")
        }

        val color = ContextCompat.getColor(this, R.color.teal_200)
        mainViewModel.getThemeSettings().observe(this) { darkmodestatus: Boolean ->

            if(darkmodestatus){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                DrawableCompat.setTint(binding.btnDetailSquat.drawable,color)
                DrawableCompat.setTint(binding.btnDetailDeadLift.drawable,color)
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
}