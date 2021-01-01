package com.ozzy.githubsearcher.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ozzy.githubsearcher.R
import com.ozzy.githubsearcher.api.model.RepositorySearchResult
import com.ozzy.githubsearcher.databinding.SearchFragmentBinding
import com.ozzy.githubsearcher.ui.search.adapter.RepositoriesAdapter
import com.ozzy.githubsearcher.util.extension.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private val adapter = RepositoriesAdapter()
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRadioGroupListener()
        initSearchInputListener()
        initAdapter()

    }

    private fun initAdapter() {
        binding.recyclerViewSearch.adapter = adapter
        viewModel.repositoriesResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RepositorySearchResult.Success -> adapter.submitList(result.data)
                else -> Log.d("TAG", "initAdapter: ")
            }
        }
    }


    private fun initRadioGroupListener() {
        binding.radioGroupSearch.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonRepositories -> binding.viewModel!!.radioCheck.postValue("Repositories")
                R.id.radioButtonUsers -> binding.viewModel!!.radioCheck.postValue("Users")
            }
        }
    }


    private fun initSearchInputListener() {
        binding.editTextSearch.setOnEditorActionListener { _: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.viewModel!!.search()
                context.hideKeyboard()
                true
            } else {
                false
            }
        }
        binding.editTextSearch.setOnKeyListener { _: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                binding.viewModel!!.search()
                context.hideKeyboard()
                true
            } else {
                false
            }
        }
    }
}