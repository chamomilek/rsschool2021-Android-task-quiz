package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.data.QuestionsRepository
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var fragmentCommutator: FragmentCommutator? = null
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var chosenOption = 0
    private var isChosen = false
    private var currentIndex: Int = 1
    private var prevAnswer: Int = 0
    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentIndex = arguments?.getInt(INDEX) ?: 1
        prevAnswer = arguments?.getInt(ANSWER) ?: 0

        question = QuestionsRepository.getData(currentIndex)

        binding.toolbar.title = TITLE + currentIndex.toString()

        if (!isChosen) {
            binding.nextButton.unable()
        }

        when (currentIndex) {
            1 -> {
                binding.previousButton.unable()
                binding.toolbar.hideNavigation()
                binding.nextButton.text = TEXT_BUTTON_NEXT
                binding.nextButton.setOnClickListener {
                    fragmentCommutator?.hasNext(
                        currentIndex,
                        chosenOption,
                        question.trueAnswer
                    )
                }
            }
            5 -> {
                binding.nextButton.text = TEXT_BUTTON_SUBMIT
                binding.toolbar.setNavigationOnClickListener {
                    fragmentCommutator?.hasPrevious(
                        currentIndex,
                        chosenOption,
                        question.trueAnswer
                    )
                }
                binding.nextButton.setOnClickListener {
                    fragmentCommutator?.hasSubmit(
                        currentIndex,
                        chosenOption,
                        question.trueAnswer
                    )
                }
            }
            else -> {
                binding.nextButton.text = TEXT_BUTTON_NEXT
                binding.toolbar.setNavigationOnClickListener {
                    fragmentCommutator?.hasPrevious(
                        currentIndex,
                        chosenOption,
                        question.trueAnswer
                    )
                }
                binding.nextButton.setOnClickListener {
                    fragmentCommutator?.hasNext(
                        currentIndex,
                        chosenOption,
                        question.trueAnswer
                    )
                }
            }
        }
        binding.previousButton.setOnClickListener {
            fragmentCommutator?.hasPrevious(
                currentIndex,
                chosenOption,
                question.trueAnswer
            )
        }


        binding.optionOne.setOnClickListener {
            chooseOption(1)
        }
        binding.optionTwo.setOnClickListener {
            chooseOption(2)
        }
        binding.optionThree.setOnClickListener {
            chooseOption(3)
        }
        binding.optionFour.setOnClickListener {
            chooseOption(4)
        }
        binding.optionFive.setOnClickListener {
            chooseOption(5)
        }

        binding.question.text = question.question
        binding.optionOne.text = question.firstAnswer
        binding.optionTwo.text = question.secondAnswer
        binding.optionThree.text = question.thirdAnswer
        binding.optionFour.text = question.fourthAnswer
        binding.optionFive.text = question.fifthAnswer

        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)

        setPreviousOption()
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (currentIndex != 1)
                fragmentCommutator?.hasPrevious(
                    currentIndex,
                    chosenOption,
                    question.trueAnswer
                ) else {
                val exitDialogFragment = ExitDialogFragment()
                childFragmentManager.let { exitDialogFragment.show(it, CHOOSER_TITLE) }
            }
        }
    }

    private fun chooseOption(number: Int) {
        chosenOption = number
        isChosen = true
        binding.nextButton.enabled()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCommutator = activity as FragmentCommutator
    }

    override fun onDestroyView() {
        _binding = null
        fragmentCommutator = null
        super.onDestroyView()
    }

    private fun setPreviousOption() {
        when (prevAnswer) {
            1 -> {
                binding.optionOne.isChosen();binding.nextButton.enabled()
            }
            2 -> {
                binding.optionTwo.isChosen();binding.nextButton.enabled()
            }
            3 -> {
                binding.optionThree.isChosen();binding.nextButton.enabled()
            }
            4 -> {
                binding.optionFour.isChosen();binding.nextButton.enabled()
            }
            5 -> {
                binding.optionFive.isChosen();binding.nextButton.enabled()
            }
        }
        chosenOption = prevAnswer

    }

    companion object {
        fun newInstance(prevAnswer: Int, index: Int): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(INDEX, index)
            args.putInt(ANSWER, prevAnswer)
            fragment.arguments = args
            return fragment
        }
        const val CHOOSER_TITLE = "Выберите способ отправки"
        const val TEXT_BUTTON_NEXT = "Next"
        const val TEXT_BUTTON_SUBMIT = "Submit"
        const val TITLE = "Question "
        const val ANSWER = "ANSWER"
        const val INDEX = "INDEX"
    }
}