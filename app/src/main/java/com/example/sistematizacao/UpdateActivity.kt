package com.example.sistematizacao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class UpdateActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val btnUpdate: Button = findViewById(R.id.btnUpdate)
        val etName: EditText = findViewById(R.id.etName)

        databaseHelper = DatabaseHelper(this)

        val id = intent.getLongExtra("id", -1)
        val name = intent.getStringExtra("name")

        etName.setText(name)

        btnUpdate.setOnClickListener{
            val newName = etName.text.toString()
            if(newName.isNotEmpty()){
                databaseHelper.updateNote(id, newName)
                finish()
            } else {
                etName.error = "O nome não pode estar vazio."
            }
        }
    }
}