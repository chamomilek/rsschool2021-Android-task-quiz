package com.rsschool.quiz.ui

import android.view.Window
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity

fun changeStatusBarColor(activity: FragmentActivity, statusBarColor : Int) {
    val window : Window = activity.window
    window.statusBarColor = ResourcesCompat.getColor(activity.resources,
       statusBarColor,null)
}