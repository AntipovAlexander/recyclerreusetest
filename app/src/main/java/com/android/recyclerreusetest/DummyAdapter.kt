package com.android.recyclerreusetest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item_dummy.view.*

class DummyAdapter : RecyclerView.Adapter<DummyAdapter.DummyHolder>() {

    private val data = mutableListOf<Int>()

    /**
     * Hash Map for storing position of the holder and its ViewPager current page*
     */
    private var viewPageStates: HashMap<Int, Int> = HashMap()

    init {
        for (i in 0 until 100) {
            data.add(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        DummyHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item_dummy, parent, false)
        )

    override fun getItemCount() = data.size

    /**
     * Bind ViewHolder, and if HashMap contains ViewPager state for this position
     * we restoring it
     */
    override fun onBindViewHolder(holder: DummyHolder, position: Int) {
        holder.bind(position)
        viewPageStates[position]?.let {
            holder.setRetainedPosition(it)
        }
    }

    /**
     * When view is recycling, we save current ViewHolder's ViewPager position to HashMap
     */
    override fun onViewRecycled(holder: DummyHolder) {
        if (holder.positionInList != RecyclerView.NO_POSITION) { // check just in case
            viewPageStates[holder.positionInList] = holder.itemView.vp.getCurrentResourcePosition()
        }
        super.onViewRecycled(holder)
    }

    class DummyHolder(view: View) : RecyclerView.ViewHolder(view) {
        var positionInList = RecyclerView.NO_POSITION // default state

        fun bind(position: Int) {
            positionInList = position
            with(itemView) {
                val list = mutableListOf<Int>()
                for (i in 0 until 5) {
                    list.add(i)
                }
                vp.fillView(list)
            }
        }

        fun setRetainedPosition(it: Int) {
            itemView.vp.setCurrentResourcePosition(it)
        }
    }
}