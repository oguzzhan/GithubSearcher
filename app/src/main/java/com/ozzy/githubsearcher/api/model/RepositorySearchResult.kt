package com.ozzy.githubsearcher.api.model

/**
 * Created by Oğuzhan Karacan on 1.01.2021.
 */
sealed class RepositorySearchResult {
    data class Success(val data: List<Repository>) : RepositorySearchResult()
    data class Error(val error: Exception) : RepositorySearchResult()
}