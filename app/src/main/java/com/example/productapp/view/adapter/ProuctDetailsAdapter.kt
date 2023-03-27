package com.example.productapp.view.adapter

import android.content.Context
import android.text.Html
import com.aezion.aerouting.driver.adapter.BaseRecyclerViewAdapter
import com.example.productapp.R
import com.example.productapp.data.model.BankProducts
import com.example.productapp.databinding.ItemProductListBinding

class ProuctDetailsAdapter(private val activity:Context):BaseRecyclerViewAdapter<BankProducts,ItemProductListBinding>() {
    override fun getLayout()= R.layout.item_product_list

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemProductListBinding>,
        position: Int
    ) {
        holder.binding.mProductDetails=items[position]
        if(items[position].features.size>0){
            holder.binding.tvProductFeatures.text=Html.fromHtml(activity.resources.getString(R.string.features)+":"+items[position].features.get(0).title)
        }else{
            holder.binding.tvProductFeatures.text=Html.fromHtml(activity.resources.getString(R.string.features)+":"+activity.resources.getString(R.string.no_features_found))
        }
        if(items[position].benefits.size>0){
            holder.binding.tvProductBenefits.text=Html.fromHtml(activity.resources.getString(R.string.benefits)+":"+items[position].benefits.get(0).description)
        }else{
            holder.binding.tvProductBenefits.text=Html.fromHtml(activity.resources.getString(R.string.benefits)+":"+activity.resources.getString(R.string.no_benefits_found))
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}