package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Person
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.Sort

class RealmViewModel:ViewModel() {
  private  val config :RealmConfiguration = RealmConfiguration.Builder().name("real_db")
      .deleteRealmIfMigrationNeeded().schemaVersion(1).compactOnLaunch().build()
    val allPersons by lazy{MutableLiveData<List<Person>>()}
    val person = Person()

    fun addPerson(id:Long,name:String,city:String,age:String) {

        val realm = Realm.getInstance(config)
        person.id = id
        person.name = name
        person.age = age
        person.city = city
        realm.beginTransaction()
        realm.insertOrUpdate(person)
        realm.commitTransaction()
        realm.close()

    }
    val  getAllPersons: RealmResults<Person>?
      get() = Realm.getInstance(config).where(Person::class.java)?.findAll()


    val sortedbyName :RealmResults<Person>?
        get() = Realm.getInstance(config).where(Person::class.java)?.findAll()?.sort("name",Sort.ASCENDING)

    val sortedbyAge :RealmResults<Person>?
        get() = Realm.getInstance(config).where(Person::class.java)?.findAll()?.sort("age",Sort.ASCENDING)
    val sortedbycity :RealmResults<Person>?
        get() = Realm.getInstance(config).where(Person::class.java)?.findAll()?.sort("city",Sort.ASCENDING)
}