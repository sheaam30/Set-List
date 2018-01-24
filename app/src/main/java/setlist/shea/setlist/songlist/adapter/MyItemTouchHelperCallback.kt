package setlist.shea.setlist.songlist.adapter

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v4.view.ViewCompat


/**
 * Created by adamshea on 1/14/18.
 */
class MyItemTouchHelperCallback(private var callbackItemTouch: CallbackItemTouch) : ItemTouchHelper.Callback() {

    private val defaultElevation = 1f //NOTE: the support library implementation uses 1f as the default

    private var isElevated = false
    private var originalElevation = 0f
    private var activeElevationChange = defaultElevation

    override fun isLongPressDragEnabled(): Boolean = false
    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN // movements drag
        return ItemTouchHelper.Callback.makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, dragFlags) // as parameter, action drag and flags drag
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }

        callbackItemTouch.itemTouchOnMove(viewHolder.adapterPosition, target.adapterPosition) // information to the interface
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when {
            (actionState == ItemTouchHelper.ACTION_STATE_DRAG && viewHolder!= null) -> {
                callbackItemTouch.onItemPicked(viewHolder.adapterPosition)
            }
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, false)
        if (isCurrentlyActive && !isElevated) {
            updateElevation(recyclerView, viewHolder, true)
        }
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder != null) {
            callbackItemTouch.onItemDropped(viewHolder.adapterPosition)
        }
        updateElevation(recyclerView, viewHolder, false)

    }

    private fun updateElevation(recyclerView: RecyclerView?, holder: RecyclerView.ViewHolder?, elevate: Boolean) {
        if (elevate) {
            originalElevation = ViewCompat.getElevation(holder?.itemView)
            val newElevation = activeElevationChange + recyclerView?.let { findMaxElevation(it) }!!
            ViewCompat.setElevation(holder?.itemView, newElevation)
            isElevated = true
        } else {
            ViewCompat.setElevation(holder?.itemView, originalElevation)
            originalElevation = 0F
            isElevated = false
        }
    }

    /**
     * Finds the elevation of the highest visible viewHolder to make sure the elevated view
     * from [.updateElevation] is above
     * all others.
     *
     * @param recyclerView The RecyclerView to use when determining the height of all the visible ViewHolders
     */
    private fun findMaxElevation(recyclerView: RecyclerView): Float {
        var maxChildElevation = 0f

        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            val elevation = ViewCompat.getElevation(child)

            if (elevation > maxChildElevation) {
                maxChildElevation = elevation
            }
        }

        return maxChildElevation
    }
}