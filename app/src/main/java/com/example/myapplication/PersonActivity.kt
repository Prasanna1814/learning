package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.OnClickListener
import com.example.myapplication.adapter.RealmAdapter
import com.example.myapplication.databinding.ActivityPersonBinding
import com.example.myapplication.model.Person
import com.example.myapplication.viewmodel.RealmViewModel
import io.realm.Realm
import io.realm.RealmResults

class PersonActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityPersonBinding
    private lateinit var viewModel: RealmViewModel
    private var realmAdapter: RealmAdapter? = null
    var list: RealmResults<Person>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[RealmViewModel::class.java]
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        addData()
        initView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun addData() {
        val id = System.currentTimeMillis()
        val name = "Prasanna"
        val city = "hydrebad"
        val age = "27"
        viewModel.addPerson(id, name, city, age)
        val id2 = System.currentTimeMillis()
        val name2 = "john"
        val city2 = "chennai"
        val age2 = "25"
        viewModel.addPerson(id2, name2, city2, age2)
        val id3 = System.currentTimeMillis()
        val name3 = "smith"
        val city3 = "dubai"
        val age3 = "23"
        viewModel.addPerson(id3, name3, city3, age3)
        val id4 = System.currentTimeMillis()
        val name4 = "cncd"
        val city4 = "vizag"
        val age4 = "24"
        viewModel.addPerson(id4, name4, city4, age4)



    }

    fun initView() {

        binding.recycler.apply {
            list = viewModel.getAllPersons
            setHasFixedSize(true)
            realmAdapter = RealmAdapter(applicationContext, list, this@PersonActivity)
            adapter = realmAdapter

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> {
                list = viewModel.sortedbyName
                realmAdapter = RealmAdapter(applicationContext, list, this)
                binding.recycler.adapter = realmAdapter

            }

            R.id.sort_by_city -> {
                list = viewModel.sortedbycity
                realmAdapter = RealmAdapter(applicationContext, list, this)
                binding.recycler.adapter = realmAdapter
            }

            R.id.sort_by_age -> {
                list = viewModel.sortedbyAge
                realmAdapter = RealmAdapter(applicationContext, list, this)
                binding.recycler.adapter = realmAdapter
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(position: Int) {
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("name", list?.get(position)!!.name)
        intent.putExtra("age", list?.get(position)!!.age)
        intent.putExtra("city", list?.get(position)!!.city)
        startActivity(intent)
    }
}