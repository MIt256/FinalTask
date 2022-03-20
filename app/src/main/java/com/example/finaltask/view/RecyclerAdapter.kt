package com.example.finaltask.view

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
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

    private var items = ArrayList<Field>()
    private var answers = mapOf<String,String>()

    fun getAnswers() = answers

    //text
    private inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TextItemBinding.bind(view)
        fun bind(item:Field) {
            binding.title.text = item.title
            binding.inputText.hint = item.name
            binding.inputText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    answers += item.name to s.toString()
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
    }
    //numeric
    private inner class NumericViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NumericItemBinding.bind(itemView)
        fun bind(item:Field) {
            binding.title.text = item.title
            binding.inputNumber.hint = item.name
            binding.inputNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    answers += item.name to s.toString()
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
            item.values?.forEach{
                if (it.key != "none") {
                    val rb = RadioButton(binding.root.context)
                    rb.text = it.value
                    binding.radioGroup.addView(rb)
                } else {answers += item.name to  it.value}

            }
            binding.radioGroup.setOnCheckedChangeListener { r, i ->
                answers += item.name to r.findViewById<RadioButton>(i).text.toString()
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