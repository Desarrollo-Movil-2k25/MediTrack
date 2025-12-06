package entity

import java.util.Date
import java.time.LocalDate

class User : Person {
    private var _nameUser: String = ""
    private var _password: String = ""

    // Constructor vacío
    constructor() : super() {
        this._nameUser = ""
        this._password = ""
    }

    // Constructor
    constructor(id: String,name: String,fLastName: String,sLastName: String,email: String,
                nameUser: String, password: String):super(id,name,fLastName,sLastName,
                                                          email)
    {
        this._nameUser = nameUser
        this._password = password
    }
    // Getters y setters
    var nameUser: String
        get() = this._nameUser
        set(value) { this._nameUser = value }

    var password: String
        get() = this._password
        set(value) { this._password = value }

    // toString
    override fun toString(): String {
        return "(${super.toString()}"
    }
}