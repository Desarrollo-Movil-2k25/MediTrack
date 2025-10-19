package entity

import java.util.Date

class User : Person {
    private var _nameUser: String = ""
    private var _password: String = ""

    // Constructor
    constructor(id: String,name: String,fLastName: String,sLastName: String, phone: Int,email: String,
                birthday: Date,province: Province,state: String,district: String, address: String,
                nameUser: String, password: String):super(id,name,fLastName,sLastName,phone,
                                                          email, birthday, province, state,
                                                          district,address)
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