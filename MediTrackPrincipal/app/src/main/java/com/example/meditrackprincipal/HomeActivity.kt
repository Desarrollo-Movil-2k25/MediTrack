package com.example.meditrackprincipal

import adapter.MedicationAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import controller.MedicationController
import kotlinx.coroutines.launch
import util.SessionManager
import util.Util

class HomeActivity : AppCompatActivity() {

    private lateinit var sesion: SessionManager
    private lateinit var medicationController: MedicationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Controlador y Sesion
        sesion = SessionManager(this)
        medicationController = MedicationController(this)

        // Footer
        val btnHome = findViewById<ImageButton>(R.id.home_button)
        btnHome.setOnClickListener { Util.OpenActivity(this, HomeActivity::class.java) }

        val btnMedication = findViewById<ImageButton>(R.id.medication_button)
        btnMedication.setOnClickListener { Util.OpenActivity(this, MedicationActivity::class.java) }

        val btnProfile = findViewById<ImageButton>(R.id.person_button)
        btnProfile.setOnClickListener { Util.OpenActivity(this, ProfileActivity::class.java) }

        val recycler = findViewById<RecyclerView>(R.id.rvMedications)
        recycler.layoutManager = LinearLayoutManager(this)

        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)

        val currentUser = sesion.getUsername()
        lifecycleScope.launch {
            try {
                val medications = medicationController.getMedicationsByUser(currentUser)

                if (medications.isEmpty()) {
                    tvEmpty.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                } else {
                    tvEmpty.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    recycler.adapter = MedicationAdapter(medications)
                }

            } catch (e: Exception) {
                tvEmpty.text = "Error cargando medicamentos"
                tvEmpty.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            }
        }
    }
}