package com.me_manas.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class newNote : AppCompatActivity() {


    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)


        val button = findViewById<Button>(R.id.submitbutton)
        button.setOnClickListener(View.OnClickListener {
            val inputeditext= findViewById<EditText>(R.id.edittext)
            val noteText = inputeditext.text.toString()
            if(noteText.isNotEmpty()){
                viewModel.insertnote(Note(noteText))
                Log.d("*#*#*#*#*","Above Toast ")
                Toast.makeText(this, "NOTE INSERTED", Toast.LENGTH_SHORT).show()
            }
        })

//        fun submitData(view: View) {
//            val inputeditext= findViewById<EditText>(R.id.edittext)
//            val noteText = inputeditext.text.toString()
//            if(noteText.isNotEmpty()){
//                viewModel.insertnote(Note(noteText))
//                Toast.makeText(this, "NOTE INSERTED", Toast.LENGTH_SHORT).show()
//
//            }
//        }
    }
}