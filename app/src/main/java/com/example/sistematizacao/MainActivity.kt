package com.example.sistematizacao

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.fabAdd)

        databaseHelper = DatabaseHelper(this)

        floatingActionButton.setOnClickListener{
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        updateRecyclerView()

    }



    private fun updateRecyclerView(){
        val notes = databaseHelper.getAllNotes()

        noteAdapter = NoteAdapter(notes){
            note: UserNote ->
            deleteNote(note)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = noteAdapter
    }
    private fun deleteNote(note: UserNote){
        databaseHelper.deleteNote(note.id)
        updateRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        val notes = databaseHelper.getAllNotes()
        recyclerView.adapter = NoteAdapter(notes){
            note: UserNote ->
            deleteNote(note)
        }
    }

}