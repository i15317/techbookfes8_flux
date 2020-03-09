package jp.digital.future.wearSupporter.data.entity

data class GithubSubmitToken(
    val access_token: String,
    val scope: String,
    val token_type: String
)