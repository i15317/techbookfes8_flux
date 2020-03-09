package jp.digital.future.wearSupporter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import jp.digital.future.wearSupporter.AppConstants.HEADER_FOOTER

class MenuRecyclerViewAdapter(private val mContext: Context, private val mItems: List<AppItem>) :
    RecyclerView.Adapter<MenuRecyclerViewAdapter.Holder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        /* Add check for viewType here if used.
        See LongListRecyclerViewAdapter for an example. */

        return Holder(mInflater.inflate(R.layout.app_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (mItems.isEmpty()) {
            return
        }
        val item = mItems[position]

        if (item.mViewType === HEADER_FOOTER) {
            return
        }

        holder.bind(item)

        // Start new activity on click of specific item.
        holder.itemView.setOnClickListener { mItems[position].launchActivity(mContext) }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].mViewType
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView
        var mImageView: ImageView

        init {
            mTextView = itemView.findViewById(R.id.icon_text_view)
            mImageView = itemView.findViewById(R.id.icon_image_view)
        }

        /** Bind appItem info to main screen (displays the item).  */
        fun bind(item: AppItem) {
            mTextView.setText(item.mItemName)
            mImageView.setImageResource(item.mImageId)
        }
    }
}