package org.on1ks.remanga.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Endpoint(
    val method: String,
    val path: String,
    val group: String,
    @SerialName("path_parameters") val pathParameters: List<String>,
    @SerialName("bearer_capable") val bearerCapable: Boolean,
    @SerialName("content_type") val contentType: String?,
    @SerialName("source_bundles") val sourceBundles: List<String>,
)

@Serializable
data class ApiSnapshot(
    @SerialName("generated_at_utc") val generatedAtUtc: String,
    @SerialName("generated_at") val generatedAt: String,
    @SerialName("source_page") val sourcePage: String,
    @SerialName("frontend_release") val frontendRelease: String,
    @SerialName("api_base_url") val apiBaseUrl: String = "https://api.remanga.org",
    @SerialName("media_base_url") val mediaBaseUrl: String = "https://remanga.org",
    @SerialName("endpoint_count") val endpointCount: Int,
    val endpoints: List<Endpoint>,
)

@Serializable
data class NamedCount(val name: String, val count: Int)

@Serializable
data class SnapshotSummary(
    @SerialName("generated_at_utc") val generatedAtUtc: String,
    @SerialName("generated_at") val generatedAt: String,
    @SerialName("source_page") val sourcePage: String,
    @SerialName("frontend_release") val frontendRelease: String,
    @SerialName("endpoint_count") val endpointCount: Int,
    val groups: List<NamedCount>,
    val methods: List<NamedCount>,
)

data class BundleSource(val path: String, val javascript: String)

data class PageMetadata(val release: String, val scriptPaths: List<String>)

fun interface ProgressListener {
    fun update(percent: Int, message: String)
}
