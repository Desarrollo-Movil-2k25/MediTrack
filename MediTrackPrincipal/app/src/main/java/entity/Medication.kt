package entity

import android.graphics.Bitmap
import java.time.LocalDate
import java.time.LocalTime

class Medication {
    private var _id: Int = 0
    private var _name: String = ""
    private var _dose: String = ""
    private var _description: String? = null
    private var _frequency: String = ""
    private lateinit var _time: String
    private lateinit var _startDate: String
    private var _endDate: String? = null
    private var _reminderActive: Boolean? = false
    private var _image: Bitmap? = null
    private var _taken: Boolean? = false
    private var _ownerUser: String = ""

    constructor()

    // Constructor
    constructor(id: Int,name: String, dose: String, description: String?,frequency:
                String,time: String, startDate: String, endDate: String?,
                reminderActive: Boolean, image: Bitmap?,taken: Boolean = false, ownerUser: String
    ) {
        this._id = id
        this._name = name
        this._dose = dose
        this._description = description
        this._frequency = frequency
        this._time = time
        this._startDate = startDate
        this._endDate = endDate
        this._reminderActive = reminderActive
        this._image = image
        this._taken = taken
        this._ownerUser = ownerUser
    }

    // Getters y Setters
    var id: Int
        get() = _id
        set(value) { _id = value }

    var name: String
        get() = _name
        set(value) { _name = value }

    var dose: String
        get() = _dose
        set(value) { _dose = value }

    var description: String?
        get() = _description
        set(value) { _description = value }

    var frequency: String
        get() = _frequency
        set(value) { _frequency = value }

    var time: String
        get() = _time
        set(value) { _time = value }

    var startDate: String
        get() = _startDate
        set(value) { _startDate = value }

    var endDate: String?
        get() = _endDate
        set(value) { _endDate = value }

    var reminderActive: Boolean?
        get() = _reminderActive
        set(value) { _reminderActive = value }

    var image: Bitmap?
        get() = _image
        set(value) { _image = value }

    var taken: Boolean?
        get() = _taken
        set(value) { _taken = value }

    var ownerUser: String
        get() = _ownerUser
        set(value) { _ownerUser = value }


    // toString
    override fun toString(): String {
        return "$_name - $_dose ($_frequency)"
    }
}
