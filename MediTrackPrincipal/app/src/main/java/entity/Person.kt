package entity

import java.time.LocalDate
import java.util.Date

open class Person {
    private var _id: String=""
    private var _name: String=""
    private var _fLastName: String=""
    private var _sLastName: String=""
    private var _phone: Int?=0
    private var _email: String=""
    private var _birthday: LocalDate?=null
    private var _province: Province?=null
    private var _state: String?=""
    private var _district: String?=""
    private var _address: String? = ""

    // Constructor vacío
    constructor()

    // Constructor
    constructor(id: String, name: String, fLastName: String, sLastName: String, phone: Int,
                email: String, birthday: LocalDate, province: Province, state: String, district: String,
                address: String){
        this._id = id
        this._name = name
        this._fLastName = fLastName
        this._sLastName = sLastName
        this._phone = phone
        this._email = email
        this._birthday = birthday
        this._province = province
        this._state = state
        this._district = district
        this._address = address
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

    var phone: Int?
        get() = this._phone
        set(value) {this._phone = value}

    var email: String
        get() = this._email
        set(value) {this._email = value}

    var birthday: LocalDate?
        get() = this._birthday
        set(value) {this._birthday = value}

    var province: Province?
        get() = this._province
        set(value) { this._province = value}

    var state: String?
        get() = this._state
        set(value) { this._state = value}

    var district: String?
        get() = this._district
        set(value) { this._district = value}

    var address: String?
        get() = this._address
        set(value) { this._address = value}

    // Mostrar nombre completo
    override fun toString(): String {
        return "$_name $_fLastName $_sLastName"
    }
}