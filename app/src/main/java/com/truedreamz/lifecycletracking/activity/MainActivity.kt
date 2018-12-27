package com.truedreamz.lifecycletracking.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.truedreamz.lifecycletracking.R
import com.truedreamz.lifecycletracking.adapter.RecyclerViewAdapter
import com.truedreamz.lifecycletracking.db.PersonModel
import com.truedreamz.lifecycletracking.view.PersonListViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnLongClickListener {

    private var viewModel: PersonListViewModel? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener { startActivity(Intent(this@MainActivity, AddPersonActivity::class.java)) })

        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewAdapter = RecyclerViewAdapter(ArrayList<PersonModel>(), this)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        recyclerView!!.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(PersonListViewModel::class.java!!)

        viewModel!!.itemAndPersonList.observe(this@MainActivity, object : Observer<List<PersonModel>> {
            override fun onChanged(itemAndPeople: List<PersonModel>?) {
                recyclerViewAdapter!!.addItems(itemAndPeople)
            }
        })

    }

    override fun onLongClick(v: View): Boolean {
        val borrowModel = v.tag as PersonModel
        viewModel!!.deleteItem(borrowModel)
        return true
    }
}