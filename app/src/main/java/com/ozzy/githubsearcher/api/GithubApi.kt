package com.ozzy.githubsearcher.api

import com.ozzy.githubsearcher.api.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Oğuzhan Karacan on 1.01.2021.
 */
interface GithubApi {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepositoriesResponse
}