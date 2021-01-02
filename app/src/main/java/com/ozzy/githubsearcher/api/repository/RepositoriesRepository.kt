package com.ozzy.githubsearcher.api.repository

import com.ozzy.githubsearcher.api.GithubApi
import com.ozzy.githubsearcher.api.model.Repository
import com.ozzy.githubsearcher.api.model.Resource
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

    private val inMemoryCache = mutableListOf<Repository>()
    private val searchResults = ConflatedBroadcastChannel<Resource<List<Repository>>>()
    private var lastRequestedPage = GITHUB_STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun getSearchResultStream(query: String): Flow<Resource<List<Repository>>> {
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
        searchResults.offer(Resource.loading())

        try {
            val response = githubApi.searchRepositories(query, lastRequestedPage, NETWORK_PAGE_SIZE)
            val repos = response.repositories ?: emptyList()
            inMemoryCache.addAll(repos)
            searchResults.offer(Resource.success(inMemoryCache))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(Resource.error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(Resource.error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}