package com.example.meditrackprincipal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import controller.UserController
import entity.User
import kotlinx.coroutines.launch
import util.SessionManager
import util.Util


class ProfileActivity : AppCompatActivity() {
    private lateinit var userController: UserController
    private lateinit var sessionManager: SessionManager
    private lateinit var txtEmail: EditText
    private lateinit var txtName: EditText
    private lateinit var txtFirstLastName: EditText
    private lateinit var txtSecondLastName: EditText

    private lateinit var tvProfileUser: TextView

    lateinit var mycontext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainProfile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializar controladores y session
        userController = UserController(this)
        sessionManager = SessionManager(this)

        // Inicializar campos
        txtEmail = findViewById(R.id.txtEmail_profile)
        txtName = findViewById(R.id.txtName_profile)
        txtFirstLastName = findViewById(R.id.txtFirstLastName_profile)
        txtSecondLastName = findViewById(R.id.txtSecondLastName_profile)
        tvProfileUser = findViewById(R.id.tvProfileUser)
        mycontext = this

        val username = sessionManager.getUsername()
        Log.d("SESSION", "Recuperado de sesión nameUser: $username")
        tvProfileUser.text = username
        //val user = "Mramores"
        searchUser(username)

        val btnUpdateProfile = findViewById<Button>(R.id.btnUpdateProfile)
        btnUpdateProfile.setOnClickListener { view ->
            val username = sessionManager.getUsername()
            Util.showDialogCondition(
                this,
                getString(R.string.MsgUpdateProfile)
            ) {
                updateProfile(username)
            }
        }

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener { view ->
            logout()
        }

        val btnHome = findViewById<ImageButton>(R.id.home_button)
        btnHome.setOnClickListener { view ->
            Util.OpenActivity(this, HomeActivity::class.java)
        }

        val btnMedication = findViewById<ImageButton>(R.id.medication_button)
        btnMedication.setOnClickListener { view ->
            Util.OpenActivity(this, MedicationActivity::class.java)
        }

        val btnProfile = findViewById<ImageButton>(R.id.person_button)
        btnProfile.setOnClickListener { view ->
            Util.OpenActivity(this, ProfileActivity::class.java)
        }
    }

    private fun updateProfile(username: String) {
        lifecycleScope.launch {
            try {
                if (username.isEmpty()) return@launch

                val userGet = userController.getUserByUsername(username)

                if (userGet != null) {

                    val user = User().apply {
                        nameUser = userGet.nameUser
                        password = userGet.password
                        id = userGet.id
                        name = txtName.text.toString()
                        fLastName = txtFirstLastName.text.toString()
                        sLastName = txtSecondLastName.text.toString()
                        email = txtEmail.text.toString()
                    }

                    userController.updateUser(user)
                    sessionManager.saveUsername(user.nameUser)

                    Toast.makeText(
                        mycontext,
                        getString(R.string.MsgUpdateProfileSuccess),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(mycontext, "ERROR: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }


    private fun searchUser(username: String) {
        lifecycleScope.launch {
            try {
                if (username.isEmpty()) return@launch

                val user = userController.getUserByUsername(username)

                if (user != null) {
                    txtEmail.setText(user.email)
                    txtName.setText(user.name)
                    txtFirstLastName.setText(user.fLastName)
                    txtSecondLastName.setText(user.sLastName)
                } else {
                    txtEmail.setText("")
                    txtName.setText("")
                    txtFirstLastName.setText("")
                    txtSecondLastName.setText("")
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun logout() {
        // Borrar la sesión
        sessionManager.clearSession()

        // Volver al login, limpiando el historial de pantallas
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()
    }
}