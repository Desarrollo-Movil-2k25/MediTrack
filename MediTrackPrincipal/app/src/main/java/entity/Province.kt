package entity

class Province {
    // THIS CLASS NEVER USED IN THE PROJECT
    private var _name: String?="";
    private var _states: MutableList<String>?=null;


    constructor()

    var Name: String?
        get()=this._name
        set(values){this._name = values}

    var States: MutableList<String>?
        get() = this._states
        set(value) {this._states=value}
}