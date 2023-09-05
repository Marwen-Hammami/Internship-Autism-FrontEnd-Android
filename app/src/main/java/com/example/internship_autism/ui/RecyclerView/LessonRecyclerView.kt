package com.example.internship_autism.ui.RecyclerView

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.internship_autism.R
import com.example.internship_autism.models.LessonModel
import com.example.internship_autism.ui.Lessons

class LessonRecyclerView constructor(private val getActivity: Lessons, private val lessonsList: List<LessonModel>) :
    RecyclerView.Adapter<LessonRecyclerView.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_card, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvLesson.text = lessonsList[position].name

        val bitmap = convertBase64ImageToBitmap(lessonsList[position].illustration)
        holder.ivLesson.setImageBitmap(bitmap)

        holder.cardView.setOnClickListener{
            Log.w("MyApp", "Click Click onBindViewHolder LessonRecyclerView - "+lessonsList[position].name)
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvLesson: TextView = itemView.findViewById(R.id.tvLesson)
        val ivLesson: ImageView = itemView.findViewById(R.id.ivLesson)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    fun convertBase64ImageToBitmap(illustration: String): Bitmap {
        // Remove the Base64 prefix (data:image/png;base64,)
        val parts = illustration.split(",")
        val base64ImageWithoutPrefix = if (parts.size > 1) parts[1] else illustration
        //Decode Base64 Image
        val imageBytes = Base64.decode(base64ImageWithoutPrefix, Base64.DEFAULT)
        // Create a Bitmap from the byte array
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

}