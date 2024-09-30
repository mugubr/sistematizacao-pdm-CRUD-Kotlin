package com.example.sistematizacao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class AddNoteActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_note)

        val btnSave: Button = findViewById(R.id.btnSave)
        val etName: EditText = findViewById(R.id.etName)

        databaseHelper = DatabaseHelper(this)

        btnSave.setOnClickListener{
            val name = etName.text.toString()
            if(name.isNotEmpty()){
                databaseHelper.addNote(name)
                finish()
            } else {
                etName.error = "O nome não pode estar vazio."
            }
        }
    }
}