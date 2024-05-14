package com.example.restfulapp.ui.duckinfo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.restfulapp.R
import com.example.restfulapp.databinding.FragmentDuckinfoBinding
import com.squareup.picasso.Picasso

class DuckinfoFragment : Fragment() {

    private var _binding: FragmentDuckinfoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDuckinfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //получаем из главной страницы имя файла
        var duckinfo = arguments?.getCharSequenceArrayList("duckinfo")
        root.findViewById<TextView>(R.id.textImageDuck).text = duckinfo?.get(0)

        if(duckinfo?.get(0)?.endsWith(".jpg") == true)
        {
            if(duckinfo?.get(1) == "true")
                Picasso.get().load("https://random-d.uk/api/http/"+duckinfo?.get(0)).into(binding.imageView)
            else
                Picasso.get().load("https://random-d.uk/api/"+duckinfo?.get(0)).into(binding.imageView)
        }
        else
            Glide.with(this).asGif().load("https://random-d.uk/api/"+duckinfo?.get(0)).into(binding.imageView)

        // переход со страницы описания дела на главную
        val buttonBack2 = root.findViewById<Button>(R.id.buttonBack2)
        buttonBack2?.setOnClickListener {
            buttonBack2.findNavController().navigate(R.id.action_navigation_duckinfo_to_navigation_ducklist)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}