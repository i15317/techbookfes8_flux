package jp.digital.future.wearSupporter

import android.app.Activity
import android.content.Context
import android.content.Intent

data class AppItem(
    val mItemName: String,
    val mImageId: Int,
    val mClass: Class<out Activity>,
    val mViewType: Int = AppConstants.NORMAL
) {
    fun launchActivity(context: Context) {
        val intent = Intent(context, mClass)
        //      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}

