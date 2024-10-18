package com.example.mycart_mvvm.allproducts.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycart_mvvm.R
import com.example.mycart_mvvm.model.Product

class ListAdapter1: DiffUtil.ItemCallback<Product> (){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem

    }
}


class AllProductsAdapter (val context: Context, private val onClick: (Product) -> Unit): ListAdapter<Product, AllProductsAdapter.ProductViewHolder>(ListAdapter1()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        Glide.with(context)
            .load(product.thumbnail) // URL or drawable
            .apply(
                RequestOptions()
                    .override(200, 200) // Resize to 200x200
                    .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                    .error(R.drawable.ic_launcher_foreground) // Error image
            )
            .into(holder.ImageView)

        holder.txtTitle.text = product.title
        holder.txtPrice.text = "$ ${product.price}"
        holder.txtBrand.text = product.brand
        holder.txtDescription.text = product.description
        holder.ratingBar.rating = product.rating.toFloat()
//        if(product.isFav)
//        {
//            holder.btnAddToFav.isEnabled = false
//        }
//        else
//        {
//            holder.btnAddToFav.isEnabled = true
//        }
        //need to pass add to fav function
        holder.btnAddToFav.setOnClickListener {
            onClick(product)
        }

    }

    class ProductViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ImageView: ImageView= itemView.findViewById(R.id.imgView)
        val txtTitle: TextView= itemView.findViewById(R.id.txtTitle)
        val txtPrice: TextView= itemView.findViewById(R.id.txtPrice)
        val txtBrand: TextView= itemView.findViewById(R.id.txtBrand)
        val txtDescription: TextView= itemView.findViewById(R.id.txtDescription)
        val ratingBar: RatingBar= itemView.findViewById(R.id.ratingBar)
        val btnAddToFav: Button= itemView.findViewById(R.id.btnAddTofav)
    }


    }