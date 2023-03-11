package com.sardordev.translator.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationBarView
import com.sardordev.translator.R
import com.sardordev.translator.databinding.FragmentMainBinding
import com.sardordev.translator.screens.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var viewPagerAdapter: PagerAdapter
    private val fragmentList = ArrayList<Fragment>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentList.add(TranslateFragment())
        fragmentList.add(SavedFragment())
        fragmentList.add(VoiceFragment())

        viewPagerAdapter = PagerAdapter(requireActivity(), fragmentList)

        binding.viewpager.adapter = viewPagerAdapter
        binding.viewpager.isUserInputEnabled = false

        binding.bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuhome -> {
                    binding.viewpager.currentItem = 0
                }
                R.id.menuvoice -> {
                    binding.viewpager.currentItem = 2
                }

                else -> {
                    binding.viewpager.currentItem = 1
                }
            }
            true
        }









        return binding.root
    }

}