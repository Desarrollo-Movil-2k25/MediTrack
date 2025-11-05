package com.example.meditrackprincipal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import controller.UserController
import util.Util


class MainActivity : AppCompatActivity() {
    private lateinit var txtNameUser: EditText
    private lateinit var txtPasswordUser: EditText
    private lateinit var userController: UserController
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userController = UserController(this)
        txtNameUser = findViewById(R.id.txtUsername_user_input)
        txtPasswordUser = findViewById(R.id.txtPassword_user_input)
        btnLogin = findViewById(R.id.login_button)
        btnCreateAccount = findViewById(R.id.create_accoutn_button)
        btnLogin.setOnClickListener {
            login()
        }
        btnCreateAccount.setOnClickListener {
            Util.OpenActivity(this, RegisterActivity::class.java)
        }
        }
    private fun validateField(value: String, field: EditText, message: String): Boolean {
        return if (value.isEmpty()) {
            field.error = message
            field.requestFocus()
            false
        } else true
    }
    private fun login() {
        val username = txtNameUser.text.toString().trim()
        val password = txtPasswordUser.text.toString().trim()

        if (!validateField(username, txtNameUser, "Username is required")) return
        if (!validateField(password, txtPasswordUser, "Password is required")) return

        try {
            val user = userController.login(username,password)
            if (user != null){
                Toast.makeText(this,R.string.MsgLoginSuccess, Toast.LENGTH_SHORT).show()
                cleanScreen()
                Util.OpenActivity(this, MedicationActivity::class.java)
            }else{
                Toast.makeText(this,R.string.MsgIsNorRegister, Toast.LENGTH_SHORT).show()
                cleanScreen()
            }

        }catch (e: Exception){
            Toast.makeText(this, e.message ?: "Error during login.", Toast.LENGTH_SHORT).show()
            cleanScreen()
        }
    }
    private fun cleanScreen(){
        txtNameUser.setText("")
        txtPasswordUser.setText("")
    }
}