package com.example.firebasefirestore.models

class MyImage {
    var name: String? = null
    var imagePath: String? = null

    constructor(name: String?, imagePath: String?) {
        this.name = name
        this.imagePath = imagePath
    }

    constructor()

}
