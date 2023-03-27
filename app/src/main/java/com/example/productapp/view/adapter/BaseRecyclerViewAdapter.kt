package com.aezion.aerouting.driver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<T : Any, VB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VB>>() {
    //DIFF_UTIL

    var items = mutableListOf<T>()

    fun addItems(items: List<T>) {
        if (items.isNotEmpty()) {
            this.items = items as MutableList<T>
            notifyDataSetChanged()
        } else {
            this.items = mutableListOf()
            notifyDataSetChanged()
        }
    }

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null


    abstract fun getLayout(): Int

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), getLayout(), parent, false
        )
    )

    /*fun BaseRecyclerViewAdapter(
        resourceId: Int,
        variableId: Int,
        baseDiffUtilItemCallback: BaseDiffUtilItemCallback<T>?,
        listItemOnClickListener: ListItemOnClickListener
    ) {
        resourceId = resourceId
        variableId = variableId
        listItemOnClickListener = listItemOnClickListener
        asyncListDiffer = AsyncListDiffer<Any>(this, baseDiffUtilItemCallback)
    }*/


    companion object {
        class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind() {
                binding.apply {
                    //mLoadListRes = obj
                    executePendingBindings()
                }
            }
        }


        /*override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }*/

        /*val DIFF_UTIL = object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                oldItem == newItem
        }*/

    }
}

