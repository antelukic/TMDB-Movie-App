package com.lukic.movieapp.utils

import com.google.android.material.tabs.TabLayout

interface TabSelectedListener : TabLayout.OnTabSelectedListener {

    override fun onTabSelected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}
