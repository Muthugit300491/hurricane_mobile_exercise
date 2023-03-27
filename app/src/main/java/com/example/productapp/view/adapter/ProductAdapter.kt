package com.example.productapp.view.adapter

import android.app.Activity
import android.content.Intent
import com.aezion.aerouting.driver.adapter.BaseRecyclerViewAdapter
import com.example.productapp.R
import com.example.productapp.data.model.Categories
import com.example.productapp.data.model.ProductDetails
import com.example.productapp.data.model.Products
import com.example.productapp.databinding.ItemCategoryListBinding
import com.example.productapp.databinding.ItemProuctListBinding
import com.example.productapp.view.activity.ProductDetailsActivity

class ProductAdapter (private val mActivity: Activity) :
    BaseRecyclerViewAdapter<Products, ItemProuctListBinding>() {
    //var mDate: String = ""
    override fun getLayout() = R.layout.item_prouct_list

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemProuctListBinding>, position: Int
    ) {
        holder.binding.mProducts = items[position]
        holder.binding.root.tag = position



        holder.binding.root.setOnClickListener {
          val intent= Intent(mActivity,ProductDetailsActivity::class.java)
            intent.putExtra("product_url",items[position].product_details)
            mActivity.startActivity(intent)
        }




    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}