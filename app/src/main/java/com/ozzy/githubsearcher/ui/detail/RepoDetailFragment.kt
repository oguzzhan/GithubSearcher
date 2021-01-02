package com.ozzy.githubsearcher.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ozzy.githubsearcher.databinding.RepoDetailFragmentBinding

class RepoDetailFragment : Fragment() {

    private val args: RepoDetailFragmentArgs by navArgs()
    private lateinit var binding: RepoDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = RepoDetailFragmentBinding.inflate(inflater, container, false)
        binding.repo = args.repo
        return binding.root
    }


}