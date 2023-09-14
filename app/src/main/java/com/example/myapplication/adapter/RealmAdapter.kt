package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding
import com.example.myapplication.model.Person
import io.realm.RealmResults

class RealmAdapter(private val context: Context, private var list: RealmResults<Person>?,private var onClickListener: OnClickListener? = null) :
    RecyclerView.Adapter<RealmAdapter.RealmViewHlder>() {
    class RealmViewHlder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealmViewHlder {
        return RealmViewHlder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: RealmViewHlder, position: Int) {
        var currentItem = list?.get(position)

        holder.binding.apply {
            name.text = currentItem?.name
            age.text = currentItem?.age
            city.text = currentItem?.city
        }
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position)
        }

    }


    override fun onViewRecycled(holder: RealmViewHlder) {
        super.onViewRecycled(holder)
        holder.binding.apply {
            name.setText(null)
            age.setText(null)
            city.setText(null)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}

interface OnClickListener {
    fun onClick(position: Int)
}


