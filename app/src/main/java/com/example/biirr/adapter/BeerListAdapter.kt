package com.example.biirr.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biirr.R
import com.example.biirr.databinding.ListItemBinding
import com.example.biirr.model.BeerAPIResponse

class BeerListAdapter (
                       private val listener: ListItemListener) :
    RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {


        var beers = mutableListOf<BeerAPIResponse>()

        fun setBeerList(movies: List<BeerAPIResponse>) {
            this.beers = movies.toMutableList()
            notifyDataSetChanged()
        }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = beers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beerItem = beers[position]
        with(holder.binding) {
            name.text = beerItem.name
            name.setTypeface(null, Typeface.BOLD)
            tagline.text = beerItem.tagline
            Glide.with(holder.itemView.context).load(beerItem.imageURL).into(holder.binding.image)
            root.setOnClickListener{
                listener.openBeer(beerItem)
            }
        }
    }

    interface ListItemListener {
        fun openBeer(beer: BeerAPIResponse)
    }
}