package com.example.testrecyclerview


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter()
    }

    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.image)!!
        val cityName = itemView.findViewById<TextView>(R.id.textView)!!
    }

    inner class MyRecyclerViewAdapter: RecyclerView.Adapter<MyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            val myViewHolder = MyViewHolder(view)
            val position = myViewHolder.adapterPosition
            if (position!=RecyclerView.NO_POSITION) {
                myViewHolder.cityName.setOnClickListener {
                    Toast.makeText(parent.context, myViewHolder.adapterPosition, Toast.LENGTH_SHORT)
                        .show()
                }
            }
            return myViewHolder
        }

        override fun getItemCount(): Int {
            return cityItems.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val listItem: ListItem = cityItems[holder.adapterPosition]
            holder.cityName.text = listItem.desc
            Glide.with(holder.image.context)
                .load(listItem.imgSrc)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.image)
        }



    }
}

data class ListItem(val imgSrc:String, val desc: String)

private val cityItems =
    listOf(
        ListItem("https://transferwise-blog.s3.amazonaws.com/Londonspringtime.jpg", "London"),
        ListItem("https://transferwise-blog.s3.amazonaws.com/Parisfrance.jpg", "Paris")

    )