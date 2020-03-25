package com.blkxltng.rawgviewer.models

import com.squareup.moshi.Json


/**
 * Models for list of games
 */
class RAWGDataResponse (
    val results: List<Game>
)

data class Game (
    val id: Int,
    val name: String,
    val platforms: List<Platform>,
    val stores: List<Store>,
    val release: String,
    val background_image: String,
    val rating: Double,
    val ratings: List<Rating>,
    val clip: Clip
)

data class Platform (
    val platform: PlatformData
)

data class PlatformData (
    val id: Int,
    val name: String
)

data class Store (
    val store: StoreData
)

data class StoreData (
    val id: Int,
    val name: String
)

data class Rating (
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
)

data class Clip (
    val clip: String,
    val clips: ClipData,
    val video: String,
    val preview: String
)

class ClipData (
    @Json(name = "320")
    val vid320: String,
    @Json(name = "640")
    val vid640: String,
    @Json(name = "full")
    val vidfull: String
)

/**
 * Models for Game Details
 */
class GameDetails (
    val id: Int,
    val name: String,
    val description: String,
    val released: String,
    val background_image: String,
    val tba: Boolean,
    val updated: String,
    val website: String,
    val rating: Double,
    val ratings: List<Rating>,
    val achievements_count: Int,
    val reddit_url: String,
    val reddit_name: String,
    val reddit_description: String,
    val metacritic_url: String,
    val parent_platforms: List<Platform>,
    val developers: List<Developer>,
    val genres: List<Genre>,
    val tags: List<Tag>,
    val publishers: List<Publisher>,
    val esrb_rating: ESRBRating,
    val clip: Clip
)

class StoreDetails (
    val id: Int,
    val url: String,
    val store: Store
)

class Developer (
    val id: Int,
    val name: String
)

class Genre (
    val id: Int,
    val name: String
)

class Tag (
    val id: Int,
    val name: String
)

class Publisher (
    val id: Int,
    val name: String
)

class ESRBRating (
    val id: Int,
    val name: String
)