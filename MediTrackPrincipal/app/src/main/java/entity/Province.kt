package entity

class Province {
    private var _name: String="";
    private lateinit var _states: MutableList<String>

    constructor()

    var Name: String
        get()=this._name
        set(values){this._name = values}

    var States: MutableList<String>
        get() = this._states
        set(value) {this._states=value}
}