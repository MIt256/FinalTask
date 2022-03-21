package com.example.finaltask.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltask.R
import com.example.finaltask.constants.Constants
import com.example.finaltask.constants.Constants.Companion.NONE
import com.example.finaltask.constants.FieldType
import com.example.finaltask.databinding.ListItemBinding
import com.example.finaltask.databinding.NumericItemBinding
import com.example.finaltask.databinding.TextItemBinding


import com.example.finaltask.data.models.Field

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recyclerViewItems = ArrayList<Field>()

    fun getAnswers() = recyclerViewItems

    //text
    private inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TextItemBinding.bind(view)
        fun bind(item: Field) {
            binding.title.text = item.title
            binding.inputText.setText(item.value)
            binding.inputText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    item.value = s.toString()
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
    }

    //numeric
    private inner class NumericViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NumericItemBinding.bind(itemView)
        fun bind(item: Field) {
            binding.title.text = item.title
            //todo
            binding.inputNumber.setText(item.value)
            binding.inputNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var number = s.toString()
                    if (number != "") {
                        if (number == ".") number += "0"
                        item.value = number.toDouble().toString()
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

    }

    //list
    private inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(itemView)
        fun bind(item: Field) {
            binding.title.text = item.title
            item.values?.forEach {
                if (it.key != NONE) {
                    val rb = RadioButton(binding.root.context)
                    rb.hint = it.key
                    rb.text = it.value
                    binding.radioGroup.addView(rb)
                    //todo
                    if (item.value == it.key)
                        rb.isChecked = true
                } else
                    if (item.value==null) item.value = it.key
            }

            binding.radioGroup.setOnCheckedChangeListener { r, i ->
                item.value = r.findViewById<RadioButton>(i).hint.toString()
            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (recyclerViewItems[position].type) {
            FieldType.TEXT -> 0
            FieldType.NUMERIC -> 1
            FieldType.LIST -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> TextViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
            )
            1 -> NumericViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.numeric_item, parent, false)
            )
            else -> ListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (recyclerViewItems[position].type) {
            FieldType.TEXT -> (holder as TextViewHolder).bind(recyclerViewItems[position])
            FieldType.NUMERIC ->  (holder as NumericViewHolder).bind(recyclerViewItems[position])
            FieldType.LIST -> (holder as ListViewHolder).bind(recyclerViewItems[position])
        }
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    fun addToList(fields: ArrayList<Field>) {
        recyclerViewItems = fields
        notifyDataSetChanged()
    }
}