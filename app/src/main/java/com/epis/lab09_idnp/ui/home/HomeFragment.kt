package com.epis.lab09_idnp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.epis.lab09_idnp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dataToSend: EditText = binding.inputHome
        val buttonGoToDashboard: Button = binding.goToDashboard

        buttonGoToDashboard.setOnClickListener {
            val result = dataToSend.text.toString()
            setFragmentResult("DataHomeToDashboard", bundleOf("bundleKey" to result))
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("DataNotificationsToHome") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            val textView: TextView = binding.textHome
            textView.text = result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}