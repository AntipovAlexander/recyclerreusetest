package com.android.recyclerreusetest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item_dummy.view.*

class DummyAdapter : RecyclerView.Adapter<DummyAdapter.DummyHolder>() {

    private val data = mutableListOf<Int>()

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

    override fun onBindViewHolder(holder: DummyHolder, position: Int) {
        holder.bind(data[position])
    }

    class DummyHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Int) {
            with(itemView) {
                val list = mutableListOf<Int>()
                for (i in 0 until 5) {
                    list.add(i)
                }
                vp.fillView(list)
            }
        }
    }
}