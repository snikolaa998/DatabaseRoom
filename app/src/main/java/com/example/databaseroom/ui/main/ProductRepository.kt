package com.example.databaseroom.ui.main

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductRepository(application: Application) {
    val searchResults = MutableLiveData<List<Product>>()
    private var productDao :ProductDao?
    val allProducts :LiveData<List<Product>>?

    init {
        val db :ProductRoomDatabase? = ProductRoomDatabase.getDatabase(application)
        productDao = db?.productDao()
        allProducts = productDao?.getAllProducts()
    }

    fun asyncFinished(results :List<Product>) {
        searchResults.value = results
    }

    private class QueryAsyncTask constructor(val asyncTaskDao: ProductDao?) :
        AsyncTask<String, Void, List<Product>>() {
        var delegate :ProductRepository? = null

        override fun doInBackground(vararg p0: String): List<Product> {
            return asyncTaskDao!!.findProduct(p0[0])
        }

        override fun onPostExecute(result: List<Product>) {
            delegate?.asyncFinished(result)
        }
    }

    private class InsertAsyncTask constructor(private val asyncTaskDao: ProductDao?) :AsyncTask<Product, Void, Void>() {
        override fun doInBackground(vararg p0: Product): Void? {
            asyncTaskDao?.insertProduct(p0[0])
            return null
        }
    }

    private class DeleteAsyncTask constructor(private val asyncTaskDao: ProductDao?) :AsyncTask<String, Void, Void>(){
        override fun doInBackground(vararg p0: String): Void? {
            asyncTaskDao?.deleteProduct(p0[0])
            return null
        }
    }

    fun insertProduct(newProduct :Product) {
        val task = InsertAsyncTask(productDao)
        task.execute(newProduct)
    }
    fun deleteProduct(name :String) {
        val task = DeleteAsyncTask(productDao)
        task.execute(name)
    }
    fun findProduct(name :String) {
        val task = QueryAsyncTask(productDao)
        task.delegate = this
        task.execute(name)

    }

}


























