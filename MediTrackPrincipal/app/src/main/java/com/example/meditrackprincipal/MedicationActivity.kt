package com.example.meditrackprincipal

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MedicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_medication)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtTime = findViewById<EditText>(R.id.txtTime_medication)
        txtTime.setOnClickListener {
            showTimePickerDialog(txtTime)
        }
    }
    private fun showTimePickerDialog(txtTime: EditText) {
        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(this, { _,selectedHour,selectedMinute ->
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            txtTime.setText(formattedTime)
        }, hour,minute, true
        )
        timePicker.show()
    }

}