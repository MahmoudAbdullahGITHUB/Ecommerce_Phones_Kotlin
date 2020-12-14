package com.example.ecomer.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomer.Model.ProductModel
import com.example.ecomer.R
import java.util.*

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    private var productList: ArrayList<ProductModel> = ArrayList<ProductModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productNameTV.setText(productList[position].getTitle())
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(productList: ArrayList<ProductModel>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productNameTV: TextView

        init {
            productNameTV = itemView.findViewById(R.id.product_name)
        }
    }
}
