package jp.digital.future.wearSupporter.data.entity

data class GithubLogin(
    val client_id: String,
    val redirect_uri: String,
    val lgoin: String,
    val scope: String,
    val state: String,
    val allow_signup: String
)