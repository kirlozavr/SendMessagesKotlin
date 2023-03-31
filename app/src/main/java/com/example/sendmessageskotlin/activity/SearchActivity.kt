package com.example.sendmessageskotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.adapters.RecyclerViewAdapterSearch
import com.example.sendmessageskotlin.common.OnClickListener
import com.example.sendmessageskotlin.dto.SearchDto
import com.example.sendmessageskotlin.service.NetworkIsConnectedService

class SearchActivity : AppCompatActivity() {

    private val QUERY = "query"

    private lateinit var searchView: SearchView
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterSearch: RecyclerViewAdapterSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            QUERY,
            searchView.query.toString()
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchView.setQuery(
            savedInstanceState.getCharSequence(QUERY),
            false
        )
    }

    private fun init() {
        constraintLayout = findViewById(R.id.constraintLayoutSearch)
        searchView = findViewById(R.id.searchView)
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    adapterSearch.deleteList()
                } else {

                }
                return false
            }

        })
    }

    private fun initRecycler(){
        val onClickListener = object : OnClickListener<SearchDto>{
            override fun onClick(entity: SearchDto, position: Int) {
                runStartActivity(entity.getUsername())
            }
        }
        recyclerView = findViewById(R.id.recycleViewSearch)
        adapterSearch = RecyclerViewAdapterSearch(this@SearchActivity, onClickListener)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterSearch
    }

    private fun isConnected() {
        val networkIsConnectedService: NetworkIsConnectedService =
            ViewModelProvider(this@SearchActivity)[NetworkIsConnectedService::class.java]
        networkIsConnectedService.isConnected(
            networkIsConnectedService,
            this@SearchActivity,
            constraintLayout
        )
    }

    private fun runStartActivity(username: String){
        val intent = Intent(
            this@SearchActivity,
            MessagesSendActivity::class.java
        )
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
}