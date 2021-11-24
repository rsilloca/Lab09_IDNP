package com.epis.lab09_idnp.ui.notifications

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
import com.epis.lab09_idnp.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dataToSend: EditText = binding.inputNotifications
        val buttonGoToDashboard: Button = binding.goToHome

        buttonGoToDashboard.setOnClickListener {
            val result = dataToSend.text.toString()
            setFragmentResult("DataNotificationsToHome", bundleOf("bundleKey" to result))
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("DataDashboardToNotifications") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            val textView: TextView = binding.textNotifications
            textView.text = result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}