package com.ozzy.githubsearcher.api.repository

import android.util.Log
import com.ozzy.githubsearcher.api.GithubApi
import com.ozzy.githubsearcher.api.model.Item
import com.ozzy.githubsearcher.api.model.RepositorySearchResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 1.01.2021.
 */
private const val GITHUB_STARTING_PAGE_INDEX = 1

@ExperimentalCoroutinesApi
class RepositoriesRepository @Inject constructor(private val githubApi: GithubApi) {

    private val inMemoryCache = mutableListOf<Item>()
    private val searchResults = ConflatedBroadcastChannel<RepositorySearchResult>()
    private var lastRequestedPage = GITHUB_STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun getSearchResultStream(query: String): Flow<RepositorySearchResult> {
        lastRequestedPage = 1
        inMemoryCache.clear()
        requestData(query)

        return searchResults.asFlow()
    }

    suspend fun requestMore(query: String) {
        if (isRequestInProgress) return
        val successful = requestData(query)
        if (successful) {
            lastRequestedPage++
        }
    }


    private suspend fun requestData(query: String): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = githubApi.searchRepositories(query, lastRequestedPage, NETWORK_PAGE_SIZE)
            Log.d("GithubRepository", "response $response")
            Log.d("GithubRepository", "response ${response.totalCount}")
            val repos = response.items ?: emptyList()
            inMemoryCache.addAll(repos)
            searchResults.offer(RepositorySearchResult.Success(inMemoryCache))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(RepositorySearchResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(RepositorySearchResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}