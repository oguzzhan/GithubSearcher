package com.ozzy.githubsearcher.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ozzy.githubsearcher.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private val args: UserDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.user = args.user
        return binding.root
    }
}