package com.android.recyclerreusetest

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewManager
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.alignParentBottom
import org.jetbrains.anko.centerHorizontally
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.support.v4.viewPager

class MyViewWithPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AspectRatioRelativeLayout(context, attrs) {

    val resourcesList = arrayListOf<Int>()

    private var pager: ViewPager

    private val adapter = object : PagerAdapter() {
        override fun getCount() = resourcesList.size

        override fun isViewFromObject(view: View, obj: Any) = view == obj

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            val text = TextView(context)
            text.text = resourcesList[position].toString()
            container.addView(text)
            return text
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }


    init {
        pager = viewPager {
            id = View.generateViewId() * (System.currentTimeMillis()).toInt()
            lparams {
                height = MATCH_PARENT
                width = MATCH_PARENT
            }
            adapter = this@MyViewWithPager.adapter
        }

        tabLayout {
            id = R.id.pagedResourcesTabs
            setupWithViewPager(pager)
        }.apply {
            (layoutParams as RelativeLayout.LayoutParams).apply {
                height = dip(40)
                alignParentBottom()
                centerHorizontally()
            }
        }
    }

    fun fillView(list: MutableList<Int>) {
        resourcesList.clear()
        resourcesList.addAll(list)

        adapter.notifyDataSetChanged()
    }

    fun getCurrentResourcePosition() = pager.currentItem

    fun setCurrentResourcePosition(page: Int) {
        if (adapter.count > 0) { // if adapter contains elements
            pager.setCurrentItem(page, false)
        }
    }

    private inline fun ViewManager.tabLayout(init: TabLayout.() -> Unit = {}) =
        ankoView({ TabLayout(it) }, R.style.GalleryPagerIndicatorStyle, init = init)
}