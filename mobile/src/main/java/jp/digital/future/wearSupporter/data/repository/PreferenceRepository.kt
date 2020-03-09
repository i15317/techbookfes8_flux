package jp.digital.future.wearSupporter.data.repository

import android.content.SharedPreferences
import jp.digital.future.wearSupporter.util.string
import javax.inject.Inject


/**
 * Preference
 */

//Todo メンバ公開してるけどこれでいいかな？
class PreferenceRepository @Inject constructor(private val preference: SharedPreferences) {
    companion object {
        const val DEFAULT_KEY = "NO VALUE"
        const val GITHUB_TOKEN_KEY = "github_token_key"
        const val GITHUB_CODE = "github_code"
    }

    //githubcode
    var github_api_code: String by preference.string(DEFAULT_KEY, GITHUB_CODE)
    //github token
    var github_api_token: String by preference.string(DEFAULT_KEY, GITHUB_TOKEN_KEY)

}
