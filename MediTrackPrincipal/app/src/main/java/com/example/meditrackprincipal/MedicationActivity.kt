package com.example.meditrackprincipal

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import controller.MedicationController
import entity.Medication
import util.SessionManager
import util.Util
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class MedicationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private val calendar = Calendar.getInstance()
    private lateinit var medicationController: MedicationController
    private lateinit var txtId: EditText
    private lateinit var txtDose: EditText
    private lateinit var txtName: EditText
    private lateinit var txtDescription: EditText
    private lateinit var txtFrequency: Spinner
    private lateinit var txtTime: EditText
    private lateinit var lbStartDate: TextView
    private lateinit var lbEndDate: TextView
    private var isEditMode: Boolean=false
    private var selectingStartDate = true
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private lateinit var imgPhoto: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_medication)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        medicationController = MedicationController(this)
        txtId = findViewById(R.id.txtId_medication)
        txtDose = findViewById(R.id.txtDose_medication)
        txtName = findViewById(R.id.txtName_medication)
        txtDescription = findViewById(R.id.txtDescription_medication)
        txtFrequency = findViewById(R.id.spnFrequency_medication)
        txtTime = findViewById(R.id.txtTime_medication)
        lbStartDate = findViewById(R.id.tvStartDate_medication)
        lbEndDate = findViewById(R.id.tvEndDate_medication)
        imgPhoto = findViewById(R.id.imgMedication)

        val btnSelectStartDate = findViewById<ImageButton>(R.id.btnSelectStartDate)
        btnSelectStartDate.setOnClickListener { view ->
            selectingStartDate = true
            showDatePickerDialog()
        }

        val btnSelectEndDate = findViewById<ImageButton>(R.id.btnSelectEndDate)
        btnSelectEndDate.setOnClickListener { view ->
            selectingStartDate = false
            showDatePickerDialog()
        }

        val txtTime = findViewById<EditText>(R.id.txtTime_medication)
        txtTime.setOnClickListener {
            showTimePickerDialog(txtTime)
        }


        val btnCancel = findViewById<ImageButton>(R.id.cancel_button)
        btnCancel.setOnClickListener { view ->
            cleanScreen()
        }

        val btnSearch = findViewById<ImageButton>(R.id.SearchMedication_button)
        btnSearch.setOnClickListener { view ->
            searchMedication(txtId.text.toString().trim().toInt())
        }

        val btnSave = findViewById<ImageButton>(R.id.save_button)
        btnSave.setOnClickListener { view ->
            Util.showDialogCondition(this, getString(R.string.MsgSaveMedication), ::saveMedication)
        }

        val btnUpdate = findViewById<ImageButton>(R.id.edit_button)
        btnUpdate.setOnClickListener { view ->
            if (isEditMode) {
                Util.showDialogCondition(this, getString(R.string.MsgUpdateMedication), ::saveMedication)
            } else {
                Toast.makeText(this, R.string.MsgNotIsEditMode, Toast.LENGTH_LONG).show()
            }

        }

        val btnDelete = findViewById<ImageButton>(R.id.delete_button)
        btnDelete.setOnClickListener { view ->
            if (isEditMode) {
                Util.showDialogCondition(this, getString(R.string.MsgDeleteMedication), ::deleteMedication)
            } else {
                Toast.makeText(this, R.string.MsgNotIsEditMode2, Toast.LENGTH_LONG).show()
            }
        }

        val btnCamera = findViewById<Button>(R.id.btnCamera_medication)
        btnCamera.setOnClickListener { view ->
            takePhoto()
        }

        val btnGallery = findViewById<Button>(R.id.btnGallery_medication)
        btnGallery.setOnClickListener { view ->
            selectPhoto()
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
    private fun getDateFormatString(dayOfMonth: Int, monthValue: Int,yearValue: Int): String{
        return "${if (dayOfMonth < 10) "0" else "" }$dayOfMonth/${if (monthValue < 10) "0" else "" }$monthValue/$yearValue"
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val formattedDate = getDateFormatString(dayOfMonth, month + 1, year)
        if (selectingStartDate) {
            lbStartDate.text = formattedDate
        } else {
            lbEndDate.text = formattedDate
        }
    }
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }
    private fun showTimePickerDialog(txtTime: EditText) {
        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val formattedTime = formatToAmPm(selectedHour, selectedMinute)
                txtTime.setText(formattedTime)
            },
            hour,
            minute,
            false // <-- usa formato AM/PM
        )
        timePicker.show()
    }
    private fun formatToAmPm(hour: Int, minute: Int): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        val amPmFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return amPmFormat.format(cal.time)
    }
    private fun invalidationData(): Boolean {
        val startDateParse = Util.parseStringToDateModern(lbStartDate.text.toString(), "dd/MM/yyyy")
        val endDateValid = lbEndDate.text.trim().isEmpty() ||
                Util.parseStringToDateModern(lbEndDate.text.toString(), "dd/MM/yyyy") != null

        return txtId.text.trim().isNotEmpty() &&
                txtName.text.trim().isNotEmpty() &&
                txtFrequency.selectedItem != null &&
                txtTime.text.trim().isNotEmpty() &&
                startDateParse != null &&
                endDateValid
    }
    private fun isInt(value: String): Boolean {
        return value.trim().toIntOrNull() != null
    }
    private fun saveMedication() {
        try {
            val medication = Medication()
            val session = SessionManager(this)
            val currentUser = session.getUsername()

            val btnSelectStartDate = findViewById<ImageButton>(R.id.btnSelectStartDate)
            if (invalidationData()) {
                // Validar duplicados
                if (medicationController.getMedicationById(txtId.text.toString().trim().toInt(),currentUser) != null && !isEditMode) {
                    Toast.makeText(this, getString(R.string.MsgDuplicated), Toast.LENGTH_LONG).show()
                    return
                }
                if (isInt(txtId.text.toString().trim())){
                    medication.id = txtId.text.toString().trim().toInt()
                }else{
                    Toast.makeText(this,R.string.MsgNotIsInt, Toast.LENGTH_LONG).show()
                    txtId.requestFocus()
                    return
                }
                medication.name = txtName.text.toString().trim()
                medication.dose = txtDose.text.toString().trim()
                medication.description = txtDescription.text.toString().trim().ifEmpty {null}
                medication.frequency = txtFrequency.selectedItem.toString()
                val timeParsed = Util.parseStringToTimeModern(txtTime.text.toString().trim(), "hh:mm a")
                if (timeParsed == null) {
                    Toast.makeText(this, "Hora inválida o no seleccionada", Toast.LENGTH_LONG).show()
                    return
                }
                medication.time = timeParsed
                // Fechas
                val startDateParsed = Util.parseStringToDateModern(lbStartDate.text.toString(), "dd/MM/yyyy")
                if (startDateParsed == null) {
                    Toast.makeText(this, "Fecha de inicio inválida", Toast.LENGTH_LONG).show()
                    return
                }
                medication.startDate = startDateParsed
                if (lbEndDate.text.toString().trim().isNotEmpty()) {
                    val endDateParsed = Util.parseStringToDateModern(lbEndDate.text.toString(), "dd/MM/yyyy")
                    medication.endDate = LocalDate.of(endDateParsed!!.year, endDateParsed.monthValue, endDateParsed.dayOfMonth)
                } else {
                    medication.endDate = null
                }
                medication.reminderActive = true
                medication.taken = false
                medication.image = (imgPhoto.drawable as BitmapDrawable).bitmap
                medication.ownerUser = currentUser
                if (!isEditMode){
                    medicationController.addMedication(medication)
                }else
                    medicationController.updateMedication(medication)
                cleanScreen()
                txtId.isEnabled = true
                btnSelectStartDate.isEnabled = true
                Toast.makeText(this,getString(R.string.MsgCreateMedicationSuccess),Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Datos incompletos o inválidos", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error: ${e.message ?: e::class.simpleName}", Toast.LENGTH_LONG).show()
            cleanScreen()
        }
    }
    private fun searchMedication(id: Int) {
        if(!isInt(id.toString().trim())){
            Toast.makeText(this,R.string.MsgNotIsInt, Toast.LENGTH_LONG).show()
            return
        }
        try {
            val session = SessionManager(this)
            val currentUser = session.getUsername()
            val btnSelectStartDate = findViewById<ImageButton>(R.id.btnSelectStartDate)
            val medication = medicationController.getMedicationById(id,currentUser)
            if (medication != null) {
                isEditMode = true
                txtId.setText(medication.id.toString())
                txtId.isEnabled = false
                txtDose.setText(medication.dose)
                txtName.setText(medication.name)
                txtDescription.setText(medication.description ?: "")
                imgPhoto.setImageBitmap(medication.image)
                val frequencyAdapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.frequency_options,
                    android.R.layout.simple_spinner_item
                )
                frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                txtFrequency.adapter = frequencyAdapter
                txtFrequency.setSelection(frequencyAdapter.getPosition(medication.frequency))
                txtTime.setText(medication.time.format(DateTimeFormatter.ofPattern("hh:mm a")))
                val startDate = medication.startDate
                lbStartDate.text = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                lbStartDate.isEnabled = false
                btnSelectStartDate.isEnabled = false
                year = startDate.year
                month = startDate.monthValue - 1
                day = startDate.dayOfMonth

                if (medication.endDate != null) {
                    val endDate = medication.endDate!!
                    lbEndDate.text = endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                } else {
                    lbEndDate.text = ""
                }
            } else {
                cleanScreen()
                Toast.makeText(this, R.string.MsgDataNotFound, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            cleanScreen()
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
    private fun deleteMedication() {
        try {
            val session = SessionManager(this)
            val currentUser = session.getUsername()
            medicationController.removeMedication(txtId.text.toString().trim().toInt(),currentUser)
            cleanScreen()
            Toast.makeText(
                this, getString(R.string.MsgDeleteSuccess),
                Toast.LENGTH_LONG
            ).show()
            txtId.isEnabled = true
        }catch (e: Exception){
            Toast.makeText(
                this, getString(R.string.ErrorMsgRemove),
                Toast.LENGTH_LONG
            ).show()
        }

    }
    private fun cleanScreen(){
        txtId.setText("")
        txtName.setText("")
        txtDose.setText("")
        txtDescription.setText("")
        txtFrequency.setSelection(0)
        txtTime.setText("")
        lbStartDate.text = ""
        lbEndDate.text = ""
        imgPhoto.setImageBitmap(null)
    }
    private val cameraPreviewLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        if (bitmap != null) {
            imgPhoto.setImageBitmap(bitmap)
        } else {
            // Image capture failed or was cancelled
        }
    }
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // Handle the selected image URI here
            data?.data?.let { imageUri ->
                imgPhoto.setImageURI(imageUri)
            }
        }
    }
    private fun takePhoto() {
        cameraPreviewLauncher.launch(null)
    }
    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*")
        selectImageLauncher.launch(intent)
    }



}