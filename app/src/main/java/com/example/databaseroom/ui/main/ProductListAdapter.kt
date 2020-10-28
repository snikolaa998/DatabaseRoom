package com.example.databaseroom.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.R
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductListAdapter(private val productItemLayout :Int) :RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var productList :List<Product>? = null

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val item = holder.item
        productList.let {
            item.text = it!![position].productName
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(productItemLayout, parent, false)
        return ViewHolder(view)
    }

    fun setProductList(products :List<Product>){
        productList = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (productList == null) 0 else productList!!.size
    }

    class ViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){
        var item :TextView = itemView.findViewById(R.id.product_row)
    }
}