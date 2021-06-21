package com.rsschool.quiz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.AnswersController
import com.rsschool.quiz.data.QuestionsRepository
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.theme.ThemeRepository

class MainActivity : AppCompatActivity(), FragmentCommutator,
    ExitDialogFragment.ExitDialogListener {

    private var currentIndex = 1
    private lateinit var binding: ActivityMainBinding
    private var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private val answersController = AnswersController()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        QuestionsRepository.setData(this)
        ThemeRepository.initThemes()
        setCustomTheme()
        setFragment(
            QuizFragment.newInstance(answersController.getRepliesAnswer(currentIndex), currentIndex)
        )
    }

    override fun hasNext(index: Int, chosenOption: Int, trueAnswer: Int) {
        currentIndex++
        if (currentIndex in indexRange) {
            setFragment(
                QuizFragment.newInstance(
                    answersController.getRepliesAnswer(currentIndex),
                    currentIndex
                )
            )
            answersController.registerAnswer(chosenOption, index, trueAnswer)
            setCustomTheme()
        }
    }

    override fun hasPrevious(index: Int, chosenOption: Int, trueAnswer: Int) {
        currentIndex--
        if (currentIndex in indexRange) {
            setFragment(
                QuizFragment.newInstance(
                    answersController.getRepliesAnswer(currentIndex),
                    currentIndex
                )
            )
            answersController.registerAnswer(chosenOption, index, trueAnswer)
            setCustomTheme()
        }
    }

    override fun hasSubmit(index: Int, chosenOption: Int, trueAnswer: Int) {
        currentIndex++
        answersController.registerAnswer(chosenOption, index, trueAnswer)
        setFragment(ResultFragment.newInstance(answersController))
        setCustomTheme()
    }

    private fun setFragment(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.container.id, fragment).commit()
    }

    private fun setCustomTheme() {
        setTheme(ThemeRepository.getTheme(currentIndex).themeLink)
        changeStatusBarColor(this,ThemeRepository.getTheme(currentIndex).colorLink)
    }

    override fun onExit() {
        finish()
    }

    companion object {
        private val indexRange = 1..5
    }

}

