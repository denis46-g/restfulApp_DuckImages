package com.example.restfulapp.ui.ducklist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.restfulapp.R
import com.example.restfulapp.data.Duck
import com.example.restfulapp.databinding.ItemDuckBinding

class DuckAdapter(viewModel: DucklistViewModel): RecyclerView.Adapter<DuckAdapter.DuckViewHolder>(), View.OnClickListener {

    var vm = viewModel

    var data: List<Duck> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class DuckViewHolder(val binding: ItemDuckBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(duck: ViewGroup, viewType: Int): DuckViewHolder {
        val inflater = LayoutInflater.from(duck.context)
        val binding = ItemDuckBinding.inflate(inflater, duck, false)

        return DuckViewHolder(binding)
    }
    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DuckViewHolder, position: Int) {

        val duck = data[position] // Получение утки из списка данных по позиции
        val context = holder.itemView.context

        with(holder.binding) {
            duckTextView.text = duck.code + duck.filetype

            duckTextView.setOnClickListener {

                var arrList: ArrayList<CharSequence?> = arrayListOf(duckTextView.text, duck.http.toString())
                var bundle = bundleOf("duckinfo" to arrList)

                duckTextView.findNavController().navigate(R.id.action_navigation_ducklist_to_navigation_duckinfo,
                    bundle)
            }
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}