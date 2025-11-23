package com.example.meditrackprincipal

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import util.Util

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnHome = findViewById<ImageButton>(R.id.home_button)
        btnHome.setOnClickListener(View.OnClickListener{ view ->
            Util.OpenActivity(this, HomeActivity::class.java)
        })

        val btnMedication = findViewById<ImageButton>(R.id.medication_button)
        btnMedication.setOnClickListener(View.OnClickListener{view ->
            Util.OpenActivity(this, MedicationActivity::class.java)
        })

        val btnProfile = findViewById<ImageButton>(R.id.person_button)
        btnProfile.setOnClickListener(View.OnClickListener { view ->
            Util.OpenActivity(this, ProfileActivity::class.java)
        })
    }
}