package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rsschool.quiz.databinding.ExitDialogBinding
import java.lang.ClassCastException

class ExitDialogFragment : DialogFragment() {

    private var _binding: ExitDialogBinding? = null
    private val binding get() = _binding!!
    private var listener : ExitDialogListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExitDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.okButton.setOnClickListener {
            listener?.onExit()
            dialog?.dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
        listener = context as ExitDialogListener
        }catch (e : ClassCastException){

        }
    }

    override fun onDestroyView() {
        _binding = null
        listener = null
        super.onDestroyView()
    }

    interface ExitDialogListener {
        fun onExit()
    }
}