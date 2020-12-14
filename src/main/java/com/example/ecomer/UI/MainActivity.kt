package com.example.ecomer.UI

import android.accessibilityservice.GestureDescription
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomer.Model.ProductModel
import com.example.ecomer.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    var recycler: RecyclerView? = null
    var myProducts: ArrayList<ProductModel>? = null
    var adapter: ProductListAdapter? = null
    var mQueryString: String? = null
    var mHandler: Handler? = null
    var call: Call<List<ProductModel?>?>? = null
    var apiInterface: ApiInterface? = null
    var toTop: ImageView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.lv_Recyclerview)
        myProducts = ArrayList<ProductModel>()
        mHandler = Handler()
        adapter = ProductListAdapter()
        this.recycler?.setAdapter(adapter)
        toTop = findViewById(R.id.to_top_list)

        toTopList()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)

        recycler?.setLayoutManager(LinearLayoutManager(this))
    }


    fun toTopList(){
        this.toTop?.setOnClickListener(View.OnClickListener {
            println("yess")
            val layoutManager = recycler
                ?.getLayoutManager() as LinearLayoutManager?
            layoutManager!!.scrollToPositionWithOffset(0, 0)
        })
    }

    fun callMe() {
        call = apiInterface?.getProducts(mQueryString)
        call?.enqueue(object : Callback<List<ProductModel?>?> {
            override fun onResponse(
                call: Call<List<ProductModel?>?>?,
                response: Response<List<ProductModel?>?>
            ) {
                System.out.println("mon title = " + response.body()?.get(0)?.getTitle())
                myProducts = response.body() as ArrayList<ProductModel>?
                println("size =-=1 " + myProducts!!.size)
                adapter?.setList(myProducts!!)
            }

            override fun onFailure(call: Call<List<ProductModel?>?>?, t: Throwable?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.nav__menu, menu)
        val menuItem = menu?.findItem(R.id.nav_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search here! (enter number)"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mHandler!!.removeCallbacksAndMessages(null)
                mHandler!!.postDelayed({
                    mQueryString = newText
                    callMe()
                }, 300)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


}