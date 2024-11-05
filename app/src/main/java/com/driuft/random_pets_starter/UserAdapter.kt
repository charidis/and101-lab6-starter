package com.driuft.random_pets_starter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class UserAdapter(private val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imageView)
        val name: TextView = itemView.findViewById(R.id.textView)
        val caption: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.user_items, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(context).load(user.url).into(holder.img)

        val stringBuilder = StringBuilder()
        stringBuilder.append(user.title)

        holder.name.text = stringBuilder
        holder.caption.text = user.explanation
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}