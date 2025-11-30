package entity

import java.time.LocalDate
import java.util.Date

open class Person {
    private var _id: String=""
    private var _name: String=""
    private var _fLastName: String=""
    private var _sLastName: String=""
    private var _email: String=""

    // Constructor vacío
    constructor()

    // Constructor
    constructor(id: String, name: String, fLastName: String, sLastName: String,email: String){
        this._id = id
        this._name = name
        this._fLastName = fLastName
        this._sLastName = sLastName
        this._email = email
    }

    // Getters y Setters
    var id: String
        get() = this._id
        set(value) { this._id = value}

    var name: String
        get() = this._name
        set(value) { this._name = value}

    var fLastName: String
        get() = this._fLastName
        set(value) { this._fLastName = value }

    var sLastName: String
        get() = this._sLastName
        set(value) {this._sLastName = value}

    var email: String
        get() = this._email
        set(value) {this._email = value}

    // Mostrar nombre completo
    override fun toString(): String {
        return "$_name $_fLastName $_sLastName"
    }
}