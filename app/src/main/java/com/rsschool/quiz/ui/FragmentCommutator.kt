package com.rsschool.quiz.ui


interface FragmentCommutator {
    fun hasNext(index: Int, chosenOption: Int, trueAnswer: Int)
    fun hasPrevious(index: Int, chosenOption: Int, trueAnswer: Int)
    fun hasSubmit(index: Int, chosenOption: Int, trueAnswer: Int)
}