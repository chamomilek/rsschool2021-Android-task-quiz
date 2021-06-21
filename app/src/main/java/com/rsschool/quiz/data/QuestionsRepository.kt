package com.rsschool.quiz.data

import android.content.Context
import com.rsschool.quiz.R

object QuestionsRepository {

    val questions: ArrayList<Question> = ArrayList()

    fun getData(index: Int): Question {
        return questions[index.minus(1)]
    }

    fun setData(context: Context) {

        val arrayOfQuestion = context.resources.getStringArray(R.array.questions)

        val arrayListAnswers: ArrayList<Array<String>> = ArrayList()

        val trueAnswers = context.resources.getIntArray(R.array.true_answers).toTypedArray()

        arrayListAnswers.add(context.resources.getStringArray(R.array.first_question_answers))
        arrayListAnswers.add(context.resources.getStringArray(R.array.second_question_answers))
        arrayListAnswers.add(context.resources.getStringArray(R.array.third_question_answers))
        arrayListAnswers.add(context.resources.getStringArray(R.array.fourth_question_answers))
        arrayListAnswers.add(context.resources.getStringArray(R.array.fifth_question_answers))

        questions.add(
            Question(
                arrayOfQuestion[0],
                arrayListAnswers[0][0],
                arrayListAnswers[0][1],
                arrayListAnswers[0][2],
                arrayListAnswers[0][3],
                arrayListAnswers[0][4],
                trueAnswers[0]
            )
        )

        questions.add(
            Question(
                arrayOfQuestion[1],
                arrayListAnswers[1][0],
                arrayListAnswers[1][1],
                arrayListAnswers[1][2],
                arrayListAnswers[1][3],
                arrayListAnswers[1][4],
                trueAnswers[1]
            )
        )

        questions.add(
            Question(
                arrayOfQuestion[2],
                arrayListAnswers[2][0],
                arrayListAnswers[2][1],
                arrayListAnswers[2][2],
                arrayListAnswers[2][3],
                arrayListAnswers[2][4],
                trueAnswers[2]
            )
        )

        questions.add(
            Question(
                arrayOfQuestion[3],
                arrayListAnswers[3][0],
                arrayListAnswers[3][1],
                arrayListAnswers[3][2],
                arrayListAnswers[3][3],
                arrayListAnswers[3][4],
                trueAnswers[3]
            )
        )

        questions.add(
            Question(
                arrayOfQuestion[4],
                arrayListAnswers[4][0],
                arrayListAnswers[4][1],
                arrayListAnswers[4][2],
                arrayListAnswers[4][3],
                arrayListAnswers[4][4],
                trueAnswers[4]
            )
        )
    }


}