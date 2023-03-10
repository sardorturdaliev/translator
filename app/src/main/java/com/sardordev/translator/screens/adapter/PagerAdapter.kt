package com.sardordev.translator.screens.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(val fragmentActivity: FragmentActivity,val list:ArrayList<Fragment>) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }


}