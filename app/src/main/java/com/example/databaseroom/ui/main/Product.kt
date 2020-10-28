package com.example.databaseroom.ui.main

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class Product {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "productId")
    var id :Int = 0
    @ColumnInfo(name = "productName")
    var productName :String? = null
    var quantity :Int = 0


    constructor(id :Int, productName :String, quantity :Int) {
        this.id = id
        this.productName = productName
        this.quantity = quantity
    }

    constructor(productName: String, quantity: Int) {
        this.quantity = quantity
        this.productName = productName
    }
}