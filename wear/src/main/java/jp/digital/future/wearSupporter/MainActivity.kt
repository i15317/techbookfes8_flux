package jp.digital.future.wearSupporter

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.ImageView

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val store = QRGenerator()

        val mImageView = findViewById<ImageView>(R.id.github_qr)
        val bitmap = store.makeBitmap("https://github.com/i15317")

        mImageView.setImageBitmap(bitmap)
        // Enables Always-on
        setAmbientEnabled()
    }
}
