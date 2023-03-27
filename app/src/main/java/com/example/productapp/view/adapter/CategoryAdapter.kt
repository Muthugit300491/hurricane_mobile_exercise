package com.example.productapp.view.adapter

import android.app.Activity
import android.content.Intent
import com.aezion.aerouting.driver.adapter.BaseRecyclerViewAdapter
import com.example.productapp.R
import com.example.productapp.data.model.Categories
import com.example.productapp.data.model.ProductDetails
import com.example.productapp.databinding.ItemCategoryListBinding
import com.example.productapp.utils.setRecyclerView

class CategoryAdapter(private val mActivity: Activity) :
    BaseRecyclerViewAdapter<Categories,ItemCategoryListBinding >() {
    //var mDate: String = ""
    override fun getLayout() = R.layout.item_category_list

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemCategoryListBinding>, position: Int
    ) {
        holder.binding.mCategories = items[position]
        holder.binding.root.tag = position

        val productAdapter= ProductAdapter(mActivity)
        holder.binding.productList.adapter=productAdapter
        mActivity.setRecyclerView(holder.binding.productList)
        items[position].products?.let { productAdapter.addItems(it) }

        /*holder.binding.root.setOnClickListener {
            mNavRoutePlan(holder)
        }*/


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}
