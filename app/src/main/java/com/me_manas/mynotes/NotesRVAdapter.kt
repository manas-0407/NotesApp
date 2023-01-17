package com.me_manas.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val lisetener: InterNotesRVAdapt): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes= ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textview = itemView.findViewById<TextView>(R.id.text)
        val deletebutton= itemView.findViewById<ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.items_notes, parent, false))
        viewHolder.deletebutton.setOnClickListener{lisetener.onItemClicked(allNotes[viewHolder.adapterPosition])}
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textview.text= currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){

        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface InterNotesRVAdapt{
    fun onItemClicked(note : Note)
}