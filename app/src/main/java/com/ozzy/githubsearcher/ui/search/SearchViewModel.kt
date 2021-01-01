package com.ozzy.githubsearcher.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ozzy.githubsearcher.api.model.RepositorySearchResult
import com.ozzy.githubsearcher.api.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(private val repositoriesRepository: RepositoriesRepository) :
    ViewModel() {

    val query = MutableLiveData<String>().apply { value = "" }
    val radioCheck = MutableLiveData<String>()

    private val _repositoriesResult = MutableLiveData<RepositorySearchResult>()
    val repositoriesResult: LiveData<RepositorySearchResult> = _repositoriesResult


    fun search() {
        viewModelScope.launch {
            query.value?.let {
                repositoriesRepository.getSearchResultStream(it)
                    .collect { result ->
                        _repositoriesResult.postValue(result)
                    }
            }
        }
    }
}