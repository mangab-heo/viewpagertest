package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        val recyclerViewItems = mutableListOf<String>()
        for (i in 1..100) {
            recyclerViewItems.add("$i")
        }

        val viewPagerItems = mutableListOf<String>().apply {
            for (i in 0..3) {
                add("Tab $i")
            }
        }

        MyPageTypeAAdapter(pageAdapterList = viewPagerItems).also {
            viewPager.adapter = it
            it.submitList(viewPagerItems)
        }

        viewPager.adapter = ConcatAdapter().apply {
            addAdapter(MyListAdapter(recyclerViewItems).also {
                it.submitList(recyclerViewItems)
            })
        }

    }
}


class MyPageTypeAAdapter(
    val pageAdapterList: ,
) : ListAdapter<MyListAdapter, MyViewPagerViewHolder>(DiffUtil) {

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<MyListAdapter>() {
            override fun areItemsTheSame(oldItem: MyListAdapter, newItem: MyListAdapter): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: MyListAdapter, newItem: MyListAdapter): Boolean {
                return true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewPagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_view_pager_item, parent, false)
        return MyViewPagerViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: MyViewPagerViewHolder, position: Int) {
        pageAdapterList.getOrNull(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.tv_page_name)?.text = it
        }
    }
}


class MyViewPagerViewHolder(itemView: View) : ViewHolder(itemView) {

}

class MyListAdapter(private val dataList: List<String>) : ListAdapter<String, MyViewHolder>(DiffUtil) {
    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_my_item, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        dataList.getOrNull(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.tv_item_title)?.text = it
        }
    }

}


class MyViewHolder(itemView: View) : ViewHolder(itemView) {


}
