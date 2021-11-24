package com.epis.lab09_idnp.ui.dashboard

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
import com.epis.lab09_idnp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dataToSend: EditText = binding.inputDashboard
        val buttonGoToDashboard: Button = binding.goToNotifications

        buttonGoToDashboard.setOnClickListener {
            val result = dataToSend.text.toString()
            setFragmentResult("DataDashboardToNotifications", bundleOf("bundleKey" to result))
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("DataHomeToDashboard") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            val textView: TextView = binding.textDashboard
            textView.text = result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}