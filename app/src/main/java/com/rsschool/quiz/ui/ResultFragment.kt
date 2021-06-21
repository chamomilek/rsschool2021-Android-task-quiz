package com.rsschool.quiz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.AnswersController
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var fragmentCommutator: FragmentCommutator? = null
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var answersController : AnswersController? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answersController = arguments?.getParcelable(PARCELABLE_TITLE)

        binding.exitButton.setOnClickListener {
            val exitDialogFragment = ExitDialogFragment()
            childFragmentManager.let { exitDialogFragment.show(it, "ResultFragment") }
        }

        binding.backButton.setOnClickListener {
            restartQuiz()

        }

        binding.resultLabelTextView.text = answersController.toString()

        binding.shareButton.setOnClickListener {
            sendMail()
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
           restartQuiz()
        }
    }

    private fun sendMail(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, QUIZ_RESULT_LABEL )
        intent.putExtra(Intent.EXTRA_TEXT, answersController?.generateEmailText())
        intent.type = INTENT_TYPE
        startActivity(Intent.createChooser(intent, CHOOSER_TITLE ))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCommutator = activity as FragmentCommutator

    }

    private fun restartQuiz(){
        activity?.finish()
        val intent = Intent(context, MainActivity::class.java )
        startActivity(intent)
    }

    override fun onDestroyView() {
        _binding = null
        fragmentCommutator = null
        super.onDestroyView()

    }

    companion object{
        fun newInstance(answersController: AnswersController) : ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putParcelable(PARCELABLE_TITLE, answersController)
            fragment.arguments = args
            return fragment
        }

        const val CHOOSER_TITLE = "Выберите способ отправки"
        const val QUIZ_RESULT_LABEL = "Результат квиза от rsschool"
        const val INTENT_TYPE = "message/rfc822 "
        const val PARCELABLE_TITLE = "Answer"
    }
}