package com.ozzy.githubsearcher.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ozzy.githubsearcher.api.model.Repository
import com.ozzy.githubsearcher.api.model.Status
import com.ozzy.githubsearcher.api.model.User
import com.ozzy.githubsearcher.api.repository.RepositoriesRepository
import com.ozzy.githubsearcher.api.repository.UsersRepository
import com.ozzy.githubsearcher.core.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    private val repositoriesRepository: RepositoriesRepository,
    private val usersRepository: UsersRepository,
) :
    ViewModel() {

    val query = MutableLiveData<String>().apply { value = "" }
    val radioCheck = MutableLiveData<String>().apply { value = Constants.SearchType.REPOSITORIES }
    val loading = MediatorLiveData<Boolean>().apply { value = false }

    private val _repositoriesResult = MutableLiveData<List<Repository>>()
    val repositoriesResult: LiveData<List<Repository>> = _repositoriesResult

    private val _usersResult = MutableLiveData<List<User>>()
    val usersResult: LiveData<List<User>> = _usersResult


    fun search() {
        viewModelScope.launch {
            loading.postValue(true)
            query.value?.let {
                when (radioCheck.value) {
                    Constants.SearchType.REPOSITORIES -> {
                        repositoriesRepository.getSearchResultStream(it)
                            .collect { result ->
                                Log.d("TAG", "search: ")
                                when (result.status) {
                                    Status.LOADING -> loading.postValue(true)
                                    Status.SUCCESS -> {
                                        loading.postValue(false)
                                        _repositoriesResult.postValue(result.data)
                                    }
                                    Status.ERROR -> {// do nothing}
                                    }
                                }
                            }
                    }
                    Constants.SearchType.USERS -> {
                        usersRepository.getSearchResultStream(it)
                            .collect { result ->
                                when (result.status) {
                                    Status.LOADING -> loading.postValue(true)
                                    Status.SUCCESS -> {
                                        loading.postValue(false)
                                        _usersResult.postValue(result.data)
                                    }
                                    Status.ERROR -> {// do nothing}
                                    }
                                }
                            }
                    }
                }

            }
        }
    }

    fun loadMore(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition >= totalItemCount) {
            val immutableQuery = query.value
            if (immutableQuery != null) {
                viewModelScope.launch {
                    when (radioCheck.value) {
                        Constants.SearchType.REPOSITORIES -> repositoriesRepository.requestMore(
                            immutableQuery)
                        Constants.SearchType.USERS -> usersRepository.requestMore(immutableQuery)
                    }
                }
            }
        }
    }

    fun clearLists() {
        _repositoriesResult.postValue(listOf())
        _usersResult.postValue(listOf())
    }
}