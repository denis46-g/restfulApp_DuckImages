package com.example.restfulapp.ui.ducklist

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restfulapp.R
import com.example.restfulapp.data.Duck
import com.example.restfulapp.databinding.FragmentDucklistBinding
import com.example.restfulapp.databinding.ItemDuckBinding
import com.example.restfulapp.page_number
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DucklistFragment : Fragment() {

    private var _binding: FragmentDucklistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var ducklistViewModel: DucklistViewModel

    private lateinit var adapter: DuckAdapter // Объект Adapter

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDucklistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itembind = ItemDuckBinding.inflate(layoutInflater)


        ducklistViewModel = ViewModelProvider(this)[DucklistViewModel::class.java]

        val manager = LinearLayoutManager(requireContext()) // LayoutManager
        adapter = DuckAdapter(ducklistViewModel) // Создание объекта

        /*var k = 1

        ducklistViewModel.GetHttp().observe(viewLifecycleOwner) {data ->
            val http = data.toMutableList()
            for(i in 0..< http.size){
                val arr = http[i].split('.')
                val c = arr[0]
                val f = "." + arr[1]
                ducklistViewModel.insert(Duck(
                    id = k,
                    http = true,
                    code = c,
                    filetype = f
                ))
                k++
            }
        }

        ducklistViewModel.GetImages().observe(viewLifecycleOwner) {data ->
            val images = data.toMutableList()
            for(i in 0..< images.size){
                val arr = images[i].split('.')
                val c = arr[0]
                val f = "." + arr[1]
                ducklistViewModel.insert(Duck(
                    id = k,
                    http = false,
                    code = c,
                    filetype = f
                ))
                k++
            }
        }

        ducklistViewModel.GetGifs().observe(viewLifecycleOwner) {data ->
            val gifs = data.toMutableList()
            for(i in 0..< gifs.size){
                val arr = gifs[i].split('.')
                val c = arr[0]
                val f = "." + arr[1]
                ducklistViewModel.insert(Duck(
                    id = k,
                    http = false,
                    code = c,
                    filetype = f
                ))
                k++
            }
        }*/

        ducklistViewModel.ducks.observe(viewLifecycleOwner, Observer { duck ->
            adapter.data = duck.filter { it.filetype == ".jpg" }.toMutableList().sortedBy { it.id }
        })

        binding.recyclerView.layoutManager = manager // Назначение LayoutManager для RecyclerView
        binding.recyclerView.adapter = adapter // Назначение адаптера для RecyclerView

        binding.textStat.text = "Ducks: images (.jpg)"

        val buttonForward = root.findViewById<Button>(R.id.buttonForward)
        val buttonBack = root.findViewById<Button>(R.id.buttonBack)

        if(page_number == 0){
            root.findViewById<Button>(R.id.buttonBack).visibility = View.INVISIBLE
            page_number = 1
        }
        else if(page_number == 2)
        {

            root.findViewById<Button>(R.id.buttonBack).visibility = View.VISIBLE
            root.findViewById<Button>(R.id.buttonForward).visibility = View.INVISIBLE

            ducklistViewModel.ducks.observe(viewLifecycleOwner, Observer { duck ->
                adapter.data = duck.filter { it.filetype == ".gif" }.toMutableList().sortedBy { it.id }
            })

            binding.textStat.text = "Ducks: gifs (.gif)"
        }
        else
            root.findViewById<Button>(R.id.buttonBack).visibility = View.INVISIBLE

        buttonForward?.setOnClickListener {

            root.findViewById<Button>(R.id.buttonBack).visibility = View.VISIBLE
            root.findViewById<Button>(R.id.buttonForward).visibility = View.INVISIBLE

            ducklistViewModel.ducks.observe(viewLifecycleOwner, Observer { duck ->
                adapter.data = duck.filter { it.filetype == ".gif" }.toMutableList().sortedBy { it.id }
            })

            binding.textStat.text = "Ducks: gifs (.gif)"

            page_number = 2
        }

        buttonBack?.setOnClickListener {

            root.findViewById<Button>(R.id.buttonForward).visibility = View.VISIBLE
            root.findViewById<Button>(R.id.buttonBack).visibility = View.INVISIBLE

            ducklistViewModel.ducks.observe(viewLifecycleOwner, Observer { duck ->
                adapter.data = duck.filter { it.filetype == ".jpg" }.toMutableList().sortedBy { it.id }
            })

            binding.textStat.text = "Ducks: images (.jpg)"

            page_number = 1
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}