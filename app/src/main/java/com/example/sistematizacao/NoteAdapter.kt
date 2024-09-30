package com.example.sistematizacao

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter (private val notes : List<UserNote>, private val onDeleteClick : (UserNote) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvName.text = note.name
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("id", note.id)
            intent.putExtra("name", note.name)
            holder.itemView.context.startActivity(intent)
        }
        holder.btnDelete.setOnClickListener{
            onDeleteClick(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }
}