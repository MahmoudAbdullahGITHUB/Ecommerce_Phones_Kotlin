package com.example.ecomer.Model

class ProductModel {
    private val id = 0
    private var title: String? = null
    private val price = 0.0
    private val description: String? = null
    private val category: String? = null
    private val image: String? = null


    fun ProductModel(title: String?) {
        this.title = title
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

}