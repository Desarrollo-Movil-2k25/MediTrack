package com.example.meditrackprincipal

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import controller.UserController
import entity.Province
import entity.User
import util.Util
import java.time.LocalDate

// Utilizar lo siguiente en caso de SI utilizar la función override de la linea 80
// DatePickerDialog.OnDateSetListener
class RegisterActivity : AppCompatActivity() {
    private lateinit var userController: UserController
    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtSLastName: EditText
    // private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    // private lateinit var lbBirthday: TextView
    // private lateinit var txtProvince: EditText
    // private lateinit var txtDistrict: EditText
    // private lateinit var txtState: EditText
    // private lateinit var txtAddress : EditText
    // private var year = 0
    // private var month = 0
    // private var day = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userController = UserController(this)
        txtUsername = findViewById(R.id.txtUsername_register)
        txtPassword = findViewById(R.id.txtPassword_register)
        txtId = findViewById(R.id.txtId_register)
        txtName = findViewById(R.id.txtName_register)
        txtLastName = findViewById(R.id.txtFirstLastName_register)
        txtSLastName = findViewById(R.id.txtSecondLastName_register)
        // txtPhone = findViewById(R.id.txtPhone_register)
        txtEmail = findViewById(R.id.txtEmail_register)
        // lbBirthday = findViewById(R.id.tvBirthdayValue_register)
        // txtProvince = findViewById(R.id.txtProvince_register)
        // txtDistrict = findViewById(R.id.txtDistrict_register)
        // txtState = findViewById(R.id.txtState_register)
        // txtAddress = findViewById(R.id.txtAddress_register)

        /*val btnSelectDate = findViewById<ImageButton>(R.id.selectDate_button)
        btnSelectDate.setOnClickListener(View.OnClickListener{view ->
            showDatePickerDialog()
        })*/

        val btnSave = findViewById<Button>(R.id.register_button)
        btnSave.setOnClickListener(View.OnClickListener{view ->
            savePerson()
        })
    }
    /*private fun getDateFormatString(dayOfMonth: Int, monthValue: Int,yearValue: Int): String{
        return "${if (dayOfMonth < 10) "0" else "" }$dayOfMonth/${if (monthValue < 10) "0" else "" }$monthValue/$yearValue"
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        lbBirthday.text = getDateFormatString(dayOfMonth, month+1, year)
    }
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }*/

    fun invalidationData(): Boolean {
        //val dateParse = Util.parseStringToDateModern(lbBirthday.text.toString(), "dd/MM/yyyy")
        return txtUsername.text.trim().isNotEmpty() && txtPassword.text.trim().isNotEmpty()
                && txtId.text.trim().isNotEmpty() && txtName.text.trim().isNotEmpty()
                && txtLastName.text.trim().isNotEmpty() && txtSLastName.text.trim().isNotEmpty()
                && txtEmail.text.trim().isNotEmpty() //&& lbBirthday.text.trim().isNotEmpty()
                //&& txtProvince.text.trim().isNotEmpty() && txtState.text.trim().isNotEmpty()
                //&& txtDistrict.text.trim().isNotEmpty() && txtAddress.text.trim().isNotEmpty()
                //&& (txtPhone.text.trim().isNotEmpty() && txtPhone.text.trim().length >= 8 && txtPhone.text.toString().trim() != "0")
                //&& dateParse != null
    }
    fun savePerson(){
        try {
            if (invalidationData()){
                if(userController.getUserByUserName(txtUsername.text.toString().trim()) != null) {
                    Toast.makeText(
                        this, getString(R.string.MsgDuplicated2),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if(userController.getUserById(txtId.text.toString().trim()) != null){
                    Toast.makeText(this,getString(R.string.MsgDuplicated),
                        Toast.LENGTH_LONG).show()
                    return
                    
                }else{
                    val user = User()
                    user.nameUser = txtUsername.text.toString()
                    user.password = txtPassword.text.toString()
                    user.id = txtId.text.toString()
                    user.name = txtName.text.toString()
                    user.fLastName = txtLastName.text.toString()
                    user.sLastName = txtSLastName.text.toString()
                    user.email = txtEmail.text.toString()
                    user.phone = null
                    user.birthday = null
                    user.province = null
                    user.district = null
                    user.state = null
                    user.address = null
                    // user.phone = txtPhone.text.toString().toInt()
                    // val bDateParse = Util.parseStringToDateModern(lbBirthday.text.toString(),"dd/MM/yyyy")
                    // user.birthday = LocalDate.of(bDateParse?.year!!,bDateParse.monthValue,bDateParse.dayOfMonth)
                    // val province = Province()
                    // province.Name= txtProvince.text.toString()
                    // user.province=province
                    // user.district = txtDistrict.text.toString()
                    // user.state = txtState.text.toString()
                    // user.address = txtAddress.text.toString()
                    userController.addUser(user)
                    Toast.makeText(this,getString(R.string.MsgCreateAccSuccess),Toast.LENGTH_LONG).show()
                    cleanScreen()
                    Util.OpenActivity(this, LoginActivity::class.java)
                }
            }else{
                Toast.makeText(this,"Datos Incompletos!!!",
                    Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(this,e.message.toString(), Toast.LENGTH_LONG).show()
            cleanScreen()
        }
    }
    private fun cleanScreen(){
        txtUsername.setText("")
        txtPassword.setText("")
        txtId.setText("")
        txtName.setText("")
        txtLastName.setText("")
        txtSLastName.setText("")
        txtEmail.setText("")
        // txtPhone.setText("")
        // lbBirthday.text = ""
        // txtProvince.setText("")
        // txtState.setText("")
        // txtDistrict.setText("")
        // txtAddress.setText("")
    }
}