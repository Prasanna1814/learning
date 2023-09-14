package com.example.myapplication.model

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required


open class Person : RealmObject() {
    @PrimaryKey
    var id: Long = 0


    var name: String? = null


    var age: String? = null


    var city: String? = null
}