package com.rsschool.quiz

import android.os.Parcel
import android.os.Parcelable
import com.rsschool.quiz.data.QuestionsRepository
import java.lang.StringBuilder

class AnswersController() : Parcelable {

    private val trueAnswers: Array<Int> = Array(5) { 0 }

    private val repliesAnswers: Array<Int> = Array(5) { 0 }


    fun getRepliesAnswer(index: Int): Int {
        return repliesAnswers[index - 1]
    }

    private fun getResult(): Int {
        var result = 0
        for (i in this.trueAnswers.indices) {
            if (this.trueAnswers[i] == this.repliesAnswers[i]) {
                result += 20
            }
        }
        return result
    }

    fun registerAnswer(numberOfAnswer: Int, index: Int, trueAnswer: Int) {
        repliesAnswers[index - 1] = numberOfAnswer
        trueAnswers[index - 1] = trueAnswer
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return StringBuilder().apply {
            append(
                "Твой результат : \n ${getResult()}%" + "\n" + when (getResult()) {
                    0 -> ZERO_PERCENT_COMMENT
                    20 -> TWENTY_PERCENT_COMMENT
                    40 -> FOURTEEN_PERCENT_COMMENT
                    60 -> SIXTEEN_PERCENT_COMMENT
                    80 -> EIGHTY_PERCENT_COMMENT
                    else -> HUNDRED_PERCENT_COMMENT
                }
            )
        }.toString()
    }

    fun generateEmailText() = this.toString() + "\n" + StringBuilder().apply {
        for (i in 1..5) {
            append(
                "\n" +
                        i + ")" + QuestionsRepository.questions[i - 1].question + "\n" +
                        "Твой ответ: " +
                        when (repliesAnswers[i - 1]) {
                            1 -> QuestionsRepository.questions[i - 1].firstAnswer
                            2 -> QuestionsRepository.questions[i - 1].secondAnswer
                            3 -> QuestionsRepository.questions[i - 1].thirdAnswer
                            4 -> QuestionsRepository.questions[i - 1].fourthAnswer
                            else -> QuestionsRepository.questions[i - 1].fifthAnswer
                        } + "\n"
            )
        }
    }.toString()

    companion object CREATOR : Parcelable.Creator<AnswersController> {
        override fun createFromParcel(parcel: Parcel): AnswersController {
            return AnswersController(parcel)
        }

        override fun newArray(size: Int): Array<AnswersController?> {
            return arrayOfNulls(size)
        }

        const val ZERO_PERCENT_COMMENT = "Очень плохо!"
        const val TWENTY_PERCENT_COMMENT = "Лучше, чем ничего..."
        const val FOURTEEN_PERCENT_COMMENT =
            "C практикой будет еще лучше!"
        const val SIXTEEN_PERCENT_COMMENT = "Хорошо!"
        const val EIGHTY_PERCENT_COMMENT = "Супер!"
        const val HUNDRED_PERCENT_COMMENT = "Отлично!!"
    }

    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

}