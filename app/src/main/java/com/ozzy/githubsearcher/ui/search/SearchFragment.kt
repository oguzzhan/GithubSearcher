package com.ozzy.githubsearcher.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozzy.githubsearcher.R
import com.ozzy.githubsearcher.core.Constants
import com.ozzy.githubsearcher.databinding.SearchFragmentBinding
import com.ozzy.githubsearcher.ui.search.adapter.RepositoriesAdapter
import com.ozzy.githubsearcher.ui.search.adapter.UsersAdapter
import com.ozzy.githubsearcher.util.extension.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private val repositoriesAdapter = RepositoriesAdapter()
    private val usersAdapter = UsersAdapter()
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRadioGroupListener()
        initSearchInputListener()
        initRepositoryAdapter()
        initScrollListener()

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerViewSearch.addItemDecoration(decoration)
    }

    private fun initRepositoryAdapter() {
        binding.viewModel!!.repositoriesResult.removeObservers(viewLifecycleOwner)
        binding.recyclerViewSearch.adapter = repositoriesAdapter
        viewModel.repositoriesResult.observe(viewLifecycleOwner) { result ->
            repositoriesAdapter.submitList(result)
            repositoriesAdapter.notifyDataSetChanged()
        }
    }

    private fun initUsersAdapter() {
        binding.viewModel!!.repositoriesResult.removeObservers(viewLifecycleOwner)
        binding.recyclerViewSearch.adapter = usersAdapter
        viewModel.usersResult.observe(viewLifecycleOwner) { result ->
            usersAdapter.submitList(result)
            usersAdapter.notifyDataSetChanged()
        }
    }


    private fun initScrollListener() {
        val layoutManager = binding.recyclerViewSearch.layoutManager as LinearLayoutManager
        binding.recyclerViewSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                binding.viewModel!!.loadMore(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    private fun initRadioGroupListener() {
        binding.radioGroupSearch.setOnCheckedChangeListener { _, checkedId ->
            binding.viewModel!!.clearLists()
            when (checkedId) {
                R.id.radioButtonRepositories -> {
                    initRepositoryAdapter()
                    binding.viewModel!!.radioCheck.postValue(Constants.SearchType.REPOSITORIES)
                }
                R.id.radioButtonUsers -> {
                    binding.viewModel!!.radioCheck.postValue(Constants.SearchType.USERS)
                    initUsersAdapter()
                }
            }
        }
    }


    private fun initSearchInputListener() {
        binding.editTextSearch.setOnEditorActionListener { _: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.viewModel!!.search()
                requireActivity().hideKeyboard()
                true
            } else {
                false
            }
        }
        binding.editTextSearch.setOnKeyListener { _: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                binding.viewModel!!.search()
                requireActivity().hideKeyboard()
                true
            } else {
                false
            }
        }
    }
}