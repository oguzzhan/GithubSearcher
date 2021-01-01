package com.ozzy.githubsearcher.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val query = MutableLiveData<String>().apply { value = "" }
    val radioCheck = MutableLiveData<String>()

    fun search(){

    }
}