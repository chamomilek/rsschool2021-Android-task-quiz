package com.rsschool.quiz.ui.theme
import com.rsschool.quiz.R

object ThemeRepository {

    private val themesList = ArrayList<Theme>()

    fun initThemes(){
        themesList.add(Theme(R.style.Theme_Quiz_First, R.color.deep_orange_100))
        themesList.add(Theme(R.style.Theme_Quiz_Second, R.color.yellow_100_dark))
        themesList.add(Theme(R.style.Theme_Quiz_Third, R.color.my_deep_blue))
        themesList.add(Theme(R.style.Theme_Quiz_Fourth, R.color.my_deep_purple))
        themesList.add(Theme(R.style.Theme_Quiz_Fifth, R.color.my_deep_green))
        themesList.add(Theme(R.style.Theme_Quiz_Six,R.color.my_deep_dark))
    }

    fun getTheme(index : Int) : Theme{
       return themesList[index - 1]
    }
}