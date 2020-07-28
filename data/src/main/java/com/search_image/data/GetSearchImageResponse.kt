package com.search_image.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class GetSearchImageResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("success")
    val success: Boolean?
) {
    @Keep
    data class Data(
        @SerializedName("account_id")
        val accountId: Int?,
        @SerializedName("account_url")
        val accountUrl: String?,
        @SerializedName("ad_config")
        val adConfig: AdConfig?,
        @SerializedName("ad_type")
        val adType: Long?,
        @SerializedName("ad_url")
        val adUrl: String?,
        @SerializedName("animated")
        val animated: Boolean?,
        @SerializedName("bandwidth")
        val bandwidth: Long?,
        @SerializedName("comment_count")
        val commentCount: Int?,
        @SerializedName("cover")
        val cover: String?,
        @SerializedName("cover_height")
        val coverHeight: Int?,
        @SerializedName("cover_width")
        val coverWidth: Int?,
        @SerializedName("datetime")
        val datetime: Long?,
        @SerializedName("description")
        val description: Any?,
        @SerializedName("downs")
        val downs: Long?,
        @SerializedName("edited")
        val edited: Long?,
        @SerializedName("favorite")
        val favorite: Boolean?,
        @SerializedName("favorite_count")
        val favoriteCount: Int?,
        @SerializedName("gifv")
        val gifv: String?,
        @SerializedName("has_sound")
        val hasSound: Boolean?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("hls")
        val hls: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("images")
        val images: List<Image?>?,
        @SerializedName("images_count")
        val imagesCount: Int?,
        @SerializedName("in_gallery")
        val inGallery: Boolean?,
        @SerializedName("in_most_viral")
        val inMostViral: Boolean?,
        @SerializedName("include_album_ads")
        val includeAlbumAds: Boolean?,
        @SerializedName("is_ad")
        val isAd: Boolean?,
        @SerializedName("is_album")
        val isAlbum: Boolean?,
        @SerializedName("layout")
        val layout: String?,
        @SerializedName("link")
        val link: String?,
        @SerializedName("looping")
        val looping: Boolean?,
        @SerializedName("mp4")
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: Int?,
        @SerializedName("nsfw")
        val nsfw: Boolean?,
        @SerializedName("points")
        val points: Int?,
        @SerializedName("privacy")
        val privacy: String?,
        @SerializedName("processing")
        val processing: Processing?,
        @SerializedName("score")
        val score: Int?,
        @SerializedName("section")
        val section: String?,
        @SerializedName("size")
        val size: Int?,
        @SerializedName("tags")
        val tags: List<Tag?>?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("topic")
        val topic: String?,
        @SerializedName("topic_id")
        val topicId: Int?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("ups")
        val ups: Int?,
        @SerializedName("views")
        val views: Int?,
        @SerializedName("vote")
        val vote: Any?,
        @SerializedName("width")
        val width: Int?
    ) {
        @Keep
        data class AdConfig(
            @SerializedName("highRiskFlags")
            val highRiskFlags: List<Any?>?,
            @SerializedName("safeFlags")
            val safeFlags: List<String?>?,
            @SerializedName("showsAds")
            val showsAds: Boolean?,
            @SerializedName("unsafeFlags")
            val unsafeFlags: List<String?>?,
            @SerializedName("wallUnsafeFlags")
            val wallUnsafeFlags: List<Any?>?
        )

        @Keep
        data class Image(
            @SerializedName("account_id")
            val accountId: Any?,
            @SerializedName("account_url")
            val accountUrl: Any?,
            @SerializedName("ad_type")
            val adType: Int?,
            @SerializedName("ad_url")
            val adUrl: String?,
            @SerializedName("animated")
            val animated: Boolean?,
            @SerializedName("bandwidth")
            val bandwidth: Long?,
            @SerializedName("comment_count")
            val commentCount: Any?,
            @SerializedName("datetime")
            val datetime: Long?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("downs")
            val downs: Any?,
            @SerializedName("edited")
            val edited: String?,
            @SerializedName("favorite")
            val favorite: Boolean?,
            @SerializedName("favorite_count")
            val favoriteCount: Any?,
            @SerializedName("gifv")
            val gifv: String?,
            @SerializedName("has_sound")
            val hasSound: Boolean?,
            @SerializedName("height")
            val height: Int?,
            @SerializedName("hls")
            val hls: String?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("in_gallery")
            val inGallery: Boolean?,
            @SerializedName("in_most_viral")
            val inMostViral: Boolean?,
            @SerializedName("is_ad")
            val isAd: Boolean?,
            @SerializedName("link")
            val link: String?,
            @SerializedName("mp4")
            val mp4: String?,
            @SerializedName("mp4_size")
            val mp4Size: Int?,
            @SerializedName("nsfw")
            val nsfw: Any?,
            @SerializedName("points")
            val points: Any?,
            @SerializedName("processing")
            val processing: Processing?,
            @SerializedName("score")
            val score: Any?,
            @SerializedName("section")
            val section: Any?,
            @SerializedName("size")
            val size: Int?,
            @SerializedName("tags")
            val tags: List<Any?>?,
            @SerializedName("title")
            val title: String?,
            @SerializedName("type")
            val type: String?,
            @SerializedName("ups")
            val ups: Any?,
            @SerializedName("views")
            val views: Int?,
            @SerializedName("vote")
            val vote: Any?,
            @SerializedName("width")
            val width: Int?
        ) {
            @Keep
            data class Processing(
                @SerializedName("status")
                val status: String?
            )
        }

        @Keep
        data class Processing(
            @SerializedName("status")
            val status: String?
        )

        @Keep
        data class Tag(
            @SerializedName("accent")
            val accent: String?,
            @SerializedName("background_hash")
            val backgroundHash: String?,
            @SerializedName("background_is_animated")
            val backgroundIsAnimated: Boolean?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("description_annotations")
            val descriptionAnnotations: DescriptionAnnotations?,
            @SerializedName("display_name")
            val displayName: String?,
            @SerializedName("followers")
            val followers: Int?,
            @SerializedName("following")
            val following: Boolean?,
            @SerializedName("is_promoted")
            val isPromoted: Boolean?,
            @SerializedName("is_whitelisted")
            val isWhitelisted: Boolean?,
            @SerializedName("logo_destination_url")
            val logoDestinationUrl: Any?,
            @SerializedName("logo_hash")
            val logoHash: Any?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("thumbnail_hash")
            val thumbnailHash: Any?,
            @SerializedName("thumbnail_is_animated")
            val thumbnailIsAnimated: Boolean?,
            @SerializedName("total_items")
            val totalItems: Int?
        ) {
            @Keep
            class DescriptionAnnotations(
            )
        }
    }
}