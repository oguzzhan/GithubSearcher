package com.ozzy.githubsearcher.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/**
 * Created by Oğuzhan Karacan on 2.01.2021.
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class UsersResponse(
    @Json(name = "total_count")
    var totalCount: Int? = null,
    @Json(name = "incomplete_results")
    var incompleteResults: Boolean? = null,
    @Json(name = "items")
    var users: List<User>? = null,
) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "login")
    var login: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "node_id")
    var nodeId: String? = null,
    @Json(name = "avatar_url")
    var avatarUrl: String? = null,
    @Json(name = "gravatar_id")
    var gravatarId: String? = null,
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "html_url")
    var htmlUrl: String? = null,
    @Json(name = "followers_url")
    var followersUrl: String? = null,
    @Json(name = "following_url")
    var followingUrl: String? = null,
    @Json(name = "gists_url")
    var gistsUrl: String? = null,
    @Json(name = "starred_url")
    var starredUrl: String? = null,
    @Json(name = "subscriptions_url")
    var subscriptionsUrl: String? = null,
    @Json(name = "organizations_url")
    var organizationsUrl: String? = null,
    @Json(name = "repos_url")
    var reposUrl: String? = null,
    @Json(name = "events_url")
    var eventsUrl: String? = null,
    @Json(name = "received_events_url")
    var receivedEventsUrl: String? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "site_admin")
    var siteAdmin: Boolean? = null,
    @Json(name = "score")
    var score: Double? = null,
) : Parcelable {
    fun getScoreText() = "Score: ${score.toString()}"
}