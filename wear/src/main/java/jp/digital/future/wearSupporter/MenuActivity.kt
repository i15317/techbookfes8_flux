package jp.digital.future.wearSupporter

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wear.widget.WearableRecyclerView
import java.util.*


class MenuActivity : FragmentActivity() {

    private lateinit var adapter: MenuRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val items = ArrayList<AppItem>()
        items.add(
            AppItem(
                getString(R.string.github), R.drawable.lists_circle, MainActivity::class.java
            )
        )
        adapter = MenuRecyclerViewAdapter(this, items)
        val recyclerView = findViewById<WearableRecyclerView>(R.id.recycler_launcher_view)
        // Customizes scrolling so items farther away form center are smaller.
        val scalingScrollLayoutCallback = ScalingScrollLayoutCallback()
        recyclerView.layoutManager = WearableLinearLayoutManager(this, scalingScrollLayoutCallback)

        recyclerView.isEdgeItemsCenteringEnabled = true
        recyclerView.adapter = adapter
        // Enables Always-on
        //setAmbientEnabled()
    }
}