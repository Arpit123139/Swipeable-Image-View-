package com.example.viewpager2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.coroutines.NonDisposableHandle.parent


class BlankFragment : Fragment() {


    private  var titleList= mutableListOf<String>("Select your current location","Scroll through variety of tasty food from nearby hotels.","Swipe left to check complete menu",
    "Add items to your cart","Place order","Scroll through our dessert section while you are waiting for your order")

    private val itemList= mutableListOf<item>(item("Select your current location",R.drawable.first),
        item("Scroll through variety of tasty food from nearby hotels.",R.drawable.second),
        item("Swipe left to check complete menu",R.drawable.third),
        item("Add items to your cart",R.drawable.fourth),
        item("Place order",R.drawable.fifth),
        item("Scroll through our dessert section while you are waiting for your order",R.drawable.sixth))

    private  var imageList= mutableListOf<Int>()
    private lateinit var adapter: ViewPageAdapter
    companion object{
        private var isTimerPaused = false
    }
    private var currentPage = 0
    private val timerHandler = Handler()
    private val autoSliderRunnable = object : Runnable {
        override fun run() {
            Log.d("Arpit",isTimerPaused.toString())
            if (isTimerPaused) {
                timerHandler.postDelayed(this, 3000)
                return
            }

            if (currentPage == adapter.itemCount - 1) {
                currentPage = 0
            } else {
                currentPage++
            }
            viewPager_2.setCurrentItem(currentPage, true)
            timerHandler.postDelayed(this, 3000) // set the delay time in milliseconds
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("Arpit","Arrived at a blank fragment")
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    private fun addToList(image:Int){
        imageList.add(image)
    }

    private fun postToList(){
        for(i:Int in 1..6){
            addToList(R.mipmap.ic_launcher)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postToList()
        adapter= ViewPageAdapter(view.context,itemList)
        viewPager_2.adapter=adapter
        view.indicator.setViewPager(viewPager_2)
        timerHandler.postDelayed(autoSliderRunnable, 5000)
        super.onViewCreated(view, savedInstanceState)


    }

     fun onActionDown(){
         isTimerPaused=true
         Log.d("Arpit","onClick")
    }
    fun onActionAbove(){
        isTimerPaused = false

        Log.d("Arpit","onActionAbove")
    }




}


