package com.example.finaltask.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltask.R
import com.example.finaltask.databinding.ListItemBinding
import com.example.finaltask.databinding.NumericItemBinding
import com.example.finaltask.databinding.TextItemBinding


import com.example.finaltask.models.Field
import kotlin.coroutines.coroutineContext

//private val items: List<Field>
class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<Field>()


    //text
    private inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TextItemBinding.bind(view)
        fun bind(item:Field) {
            binding.title.text = item.title
            binding.inputText.hint = item.name
        }
    }
    //numeric
    private inner class NumericViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NumericItemBinding.bind(itemView)
        fun bind(item:Field) {
            binding.title.text = item.title
            binding.inputNumber.hint = item.name
        }
    }
    //list
    private inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(itemView)
        fun bind(item:Field) {
            binding.title.text = item.title
            for (i in 0..2) {
                val rb = RadioButton(binding.root.context)
                rb.text = item.values.v1
                binding.radioGroup.addView(rb)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position].type){
            "TEXT" -> 0
            "NUMERIC" -> 1
            else -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            0 -> TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false))
            1 -> NumericViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.numeric_item, parent, false))
            else -> ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position].type) {
            "TEXT" -> (holder as TextViewHolder).bind(items[position])
            "NUMERIC" -> (holder as NumericViewHolder).bind(items[position])
            else -> (holder as ListViewHolder).bind(items[position])
        }
    }
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(items[position])
//    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addToList(fields: ArrayList<Field>) {
        items = fields
        notifyDataSetChanged()
    }


    //notifyDataSetChanged()
}