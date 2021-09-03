package com.proway.heydocapp.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.proway.heydocapp.R

fun FragmentActivity.replaceView(fragment: Fragment, @IdRes containerId: Int = R.id.container){
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}