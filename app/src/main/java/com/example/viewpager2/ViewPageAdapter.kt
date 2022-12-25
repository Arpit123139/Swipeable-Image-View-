package com.example.viewpager2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class ViewPageAdapter(private var context: Context, private var itemList:List<item>):RecyclerView.Adapter<ViewPageAdapter.Pager2ViewHolder>() {


    inner class Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView =itemView.findViewById(R.id.title)
        val image:ImageView=itemView.findViewById(R.id.image);



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return  Pager2ViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text=itemList[position].title
        holder.image.setImageResource(itemList[position].image)



        holder.image.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action==MotionEvent.ACTION_DOWN){
                BlankFragment().onActionDown()
            }
            if(motionEvent.action==MotionEvent.ACTION_UP){
                BlankFragment().onActionAbove()
            }
            true

        }



    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}